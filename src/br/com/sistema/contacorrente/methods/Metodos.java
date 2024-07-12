package br.com.sistema.contacorrente.methods;

import java.sql.Date;
import java.util.Random;
import java.util.Scanner;

import br.com.sistema.contacorrente.dao.ContaCorrenteDAO;
import br.com.sistema.contacorrente.models.Contas;
import br.com.sistema.contacorrente.models.Movimentacoes;

public class Metodos {
	ContaCorrenteDAO contaCorrenteDao = new ContaCorrenteDAO();
	Scanner ler = new Scanner(System.in);	
	Random aleat = new Random();
	
	@SuppressWarnings("deprecation")
	public void cadastrarConta() {
		Contas conta = new Contas();
		
		while(true) {
			boolean verificado = true;
			int cod = aleat.nextInt(10000, 100000);
			
			for(Contas c: contaCorrenteDao.getContas()) {
				if(c.getCod() == cod) {
					verificado = false;
					break;
				}
			}
			
			if(verificado) {
				conta.setCod(cod);
				break;
			}
		}
		
		System.out.println("Informe seu nome completo: ");
		conta.setNomeCliente(ler.nextLine());
		
		System.out.println("Informe o tipo da conta: \n 1 - Física \n 2 - Conjunta \n 3 - Jurídica \n 4 - especial");
        conta.setTipoConta(ler.nextInt());
        
		while(true) {
			System.out.println("Deseja realizar um primeiro depósito? \n 1 - Sim \n 2 - Não");
			int opc = ler.nextInt();
            ler.nextLine();
            
            if (opc == 1) {
				while(true) {
					System.out.println("Informe o valor do depósito: ");
					double deposito = ler.nextDouble();
					ler.nextLine();
					double limite = 0;
					
					if(deposito > 0.1) {
						conta.setSaldo(deposito);
						
						if(deposito > 1000) {
							limite = deposito / 5;
						}else {
							limite = 100;
						}
						
						if(deposito > limite){
                            switch(conta.getTipoConta()){
                                case 1:
                                    limite += 0;
                                    break;
                                case 2:
                                	 limite += limite;
                                    break;
                                case 3:
                                	 limite += (deposito * 0.5);
                                    break;
                                case 4:
                                	 limite += deposito;
                                    break;
                            }
                        }
						
						conta.setLimite(limite);
						
						break;
					}else {
						System.out.println("Valor inválido!");
					}
				}
				break;
			} else if (opc == 2) {
				conta.setSaldo(0.0);
				conta.setLimite(500.0);
				break;
			} else {
				System.out.println("Opção inválida!");
			}
		}
		
		while(true) {
			System.out.println("Escolha a dia de vencimento entre: '5' '10' '15' '20' '25'");
			int diaVenc = ler.nextInt();
			Date hoje = new Date(System.currentTimeMillis());
			Date venc = null;
			boolean verificaData = false;
			
			if(diaVenc == 5 || diaVenc == 10 || diaVenc == 15 || diaVenc == 20 || diaVenc == 25) {
				if(diaVenc > hoje.getDate()) {
					if(hoje.getMonth() == 11) {
						venc = new Date(hoje.getYear() + 1, 0, diaVenc);
						verificaData = true;
					}else {
						venc = new Date(hoje.getYear(), hoje.getMonth(), diaVenc);
						verificaData = true;
					}
				}else {
					venc = new Date(hoje.getYear(), hoje.getMonth() + 1, diaVenc);
					verificaData = true;
				}
			}else {
				System.out.println("Opção inválida!");
			}
			
			if(verificaData) {
				conta.setVencimento(venc);
				break;
			}
			
		}
		
		System.out.println("O código da sua conta é: "+conta.getCod());
		System.out.println("Seu limite é de R$"+conta.getLimite());
		
		contaCorrenteDao.saveContas(conta);
	}
	
	public void cadastrarMovimento() {
		Movimentacoes movimento = new Movimentacoes();
		boolean verificado = false;
		boolean movimentou = true;
		
		while(!verificado) {
			System.out.println("Informe o código da sua conta: ");
			int cod = ler.nextInt();
			ler.nextLine();
			
			for(Contas c: contaCorrenteDao.getContas()) {
				if(c.getCod() == cod) {
					atualizarLimite(cod);
					movimento.setCod(cod);
					verificado = true;
					break;
				}
			}
		}
		
		while(true) {
			System.out.println("Informe o tipo do movimento:  \n 1 - Crédito(Depósito) \n 2 - Débito(Pagamento)");
			int tipo = ler.nextInt();
			ler.nextLine();
			
			boolean verificaTipo = false;
			Double valor = 0.0;
			
			if(tipo != 1 && tipo != 2) {
				System.out.println("Opção inválida! Informe um tipo de movimento válido");
			}else {
				verificaTipo = true;
				movimento.setTipoMovimento(tipo);
			}
			
			if(verificaTipo) {
				System.out.println("Informe o valor do movimento: ");
				valor = ler.nextDouble();
				ler.nextLine();
			}
			
			if(tipo == 2) {
				for(Contas c: contaCorrenteDao.getContas()) {
					if(c.getCod() == movimento.getCod()) {
						if(c.getLimite() < valor) {
							double din = valor - c.getLimite();
							if(c.getSaldo() >= din) {
								while(true) {
									System.out.println("O limite da conta '" +c.getLimite()+ "' é insuficiente para realizar esta movimentação! \n Deseja continuar a operação utilizando R$" +din+ " da sua conta corrente que possuí R$" +c.getSaldo()+ ". \n 1 - Sim \n 2 - Não");
									int opc = ler.nextInt();
									ler.nextLine();
									
									if(opc == 1) {
										movimento.setValorMovimento(valor);
										break;
									}else if(opc == 2) {
										movimentou = false;
										break;
									}else {
										System.out.println("Opção inválida!");
									}
								}
							}else {
								System.out.println("Não há saldo suficiente para efetuar a movimentação.");
								movimentou = false;
							}
						}else {
							movimento.setValorMovimento(valor);
						}
						break;
					}
				}
				break;
			}else if(tipo == 1){
				movimento.setValorMovimento(valor);
				break;
			}
		}
		
		if(movimentou) {
			movimento.setDataMovimentacao(new Date(System.currentTimeMillis()));
			
			contaCorrenteDao.saveMovimentacoes(movimento);
			
			atualizarConta(movimento);
		}
	}
	
	public void atualizarConta(Movimentacoes m) {
		double din = 0.0;
		for(Contas c: contaCorrenteDao.getContas()) {
			if(c.getCod() == m.getCod()) {
				if(m.getTipoMovimento() == 1) {
					din = c.getSaldo() + m.getValorMovimento();
					c.setSaldo(din);
				}else if(m.getTipoMovimento() == 2) {
					if(c.getLimite() < m.getValorMovimento()) {
						din = m.getValorMovimento() - c.getLimite();
						c.setSaldo(c.getSaldo() - din);
						c.setLimite(0.0);
					}else {
						c.setLimite(c.getLimite() - m.getTipoMovimento());
					}
				}
				
				System.out.println("O saldo da conta pós movimentação é de R$" +c.getSaldo());
				System.out.println("O limite da conta pós movimentação é de R$" +c.getLimite());
				System.out.println("A próxima data de vencimento é " +c.getVencimento());
				
				contaCorrenteDao.update(c);
				
				break;
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void atualizarLimite(int cod) {
		double din = 0.0;
		double limite = 0.0;
		int cta = 0;
		
		for(Contas c: contaCorrenteDao.getContas()) {
			if(c.getCod() == cod) {
				for(Movimentacoes m: contaCorrenteDao.getMovimentacoes()) {
					if(m.getCod() == cod) {
						din = m.getValorMovimento();
						cta++;
					}
				}
				
				din /= cta;
				
				Date hoje = new Date(System.currentTimeMillis());
				Date venc = c.getVencimento();
				Date prox = null;
				
				limite += c.getLimite();
				
				if((venc.getMonth() == hoje.getMonth() && venc.getDate() <= hoje.getDate()) || (hoje.getMonth() == (venc.getMonth() + 1) && venc.getDate() <= hoje.getDate())) {
					if(din == 0) {
						limite += din;
					}else if(din > 5000) {
						limite += din * 0.07;
					}else if(din > 3000 && din < 5000) {
						limite += din * 0.19;
					}else if(din > 1000 && din < 3000) {
						limite += din * 0.61;
					}else if(din < 1000) {
						limite = din * 2.03;
					}
					
                    switch(c.getTipoConta()){
                        case 1:
                            limite += 0;
                            break;
                        case 2:
                        	 limite += limite;
                            break;
                        case 3:
                        	 limite += (din * 0.25);
                            break;
                        case 4:
                        	 limite += (din * 0.5);
                            break;
                    }
	                
					c.setLimite(limite);
						
					if(hoje.getMonth() == 11) {
						prox = new Date(hoje.getYear() + 1, 0, venc.getDate());
					}else {
						prox = new Date(hoje.getYear(), hoje.getMonth() + 1, venc.getDate());
					}
				}
				
				c.setVencimento(prox);
				
				contaCorrenteDao.update(c);
				break;
			}
		}
	}
	
	public void consultarSaldo() {
		System.out.println("Informe o código da conta: ");
        int cod = ler.nextInt();
        ler.nextLine();
        boolean encontrado = false;
        
        for(Contas c: contaCorrenteDao.getContas()) {
        	if(c.getCod() == cod) {
        		encontrado = true;
        		System.out.println("O saldo da conta é de R$"  +c.getSaldo());
        		break;
        	}
        }
        
        if(!encontrado) {
        	System.out.println("Conta de código '" + cod + "' não encontrada! Por favor, verifique o número e digite novamente!");
        }
	}
	
	public void consultarMovimentacoes() {
		System.out.println("Informe o código da conta: ");
        int cod = ler.nextInt();
        ler.nextLine();
        boolean encontrado = false;
        
        for(Movimentacoes m: contaCorrenteDao.getMovimentacoes()) {
        	if(m.getCod() == cod) {
        		encontrado = true;
        		System.out.println("Houve um movimento de R$"  +m.getValorMovimento()+ " na data de " +m.getDataMovimentacao());
        	}
        }
        
        if(!encontrado) {
        	System.out.println("Conta de código '" + cod + "' não encontrada! Por favor, verifique o número e digite novamente!");
        }
	}
	
	public void excluirCadastro() {
		System.out.println("Informe o código da conta: ");
        int cod = ler.nextInt();
        ler.nextLine();
        boolean encontrado = false;
        
        for(Contas c: contaCorrenteDao.getContas()) {
        	if(c.getCod() == cod) {
        		encontrado = true;
        		while(true) {
	        		System.out.println("Deseja realmente excluir a conta corrente '" +cod+ "'? \n Essa ação não poderá ser alterada futuramente! \n Digite 1 para continuar e 2 para cancelar");
	        		int opc = ler.nextInt();
					ler.nextLine();
	        		
					if(opc == 1) {
						System.out.println("A conta corrente '" +cod+ "' foi EXCLUÍDA com sucesso.");
						contaCorrenteDao.deleteById(c.getId());
						break;
					}else if(opc == 2){
						System.out.println("A conta foi MANTIDA.");
						break;
					}else {
						System.out.println("Opção inválida!");
					}
        		}
        		break;
        	}
        }
        
        if(!encontrado) {
        	System.out.println("Conta de código '" + cod + "' não encontrada! Por favor, verifique o número e digite novamente!");
        }
	}
}
