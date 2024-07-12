package br.com.sistema.eleicao.methods;

import java.util.Scanner;
import java.util.Random;

import br.com.sistema.eleicao.dao.EleicaoDAO;
import br.com.sistema.eleicao.models.CadastroEleitor;
import br.com.sistema.eleicao.models.Votacao;

public class Metodos {
	EleicaoDAO eleicaoDao = new EleicaoDAO();
	Scanner ler = new Scanner(System.in);
	Random aleat = new Random();
	
	public void cadastrarEleitor() {
		CadastroEleitor eleitor = new CadastroEleitor();
		while(true){
			boolean verificado = true;
			String bloco1 = String.valueOf(aleat.nextInt(1000, 10000));
			String bloco2 = String.valueOf(aleat.nextInt(1000, 10000));
			String bloco3 = String.valueOf(aleat.nextInt(1000, 10000));
			String numEleitor = bloco1 + bloco2 + bloco3;
			
			for(CadastroEleitor ce: eleicaoDao.getEleitor()) {
				if(ce.getNumEleitor() == Long.parseLong(numEleitor)) {
					verificado = false;
					break;
				}
			}
			
			if(verificado) {
				eleitor.setNumEleitor(Long.parseLong(numEleitor));
				break;
			}
		}
		
		System.out.println("Informe o Nome do Eleitor: ");
		eleitor.setNomeEleitor(ler.nextLine());
		
		eleitor.setSecao(aleat.nextInt(1, 1000));
		
		eleitor.setZona(aleat.nextInt(1, 500));
		
		eleicaoDao.saveEleitor(eleitor);
		
		System.out.println("\n Seu Número de Eleitor é: "+eleitor.getNumEleitor());
		System.out.println("Sua Seção Eleitoral é: "+eleitor.getSecao());
		System.out.println("Seu Zona Eleitoral é: "+eleitor.getZona()+ "\n");
	}
	
	public void cadastrarVotacoes() {
		Votacao votacao = new Votacao();
		boolean verificado = false, votado = false, existe = false;
		while(!verificado) {
			System.out.println("Informe a Seção desta votação: ");
			votacao.setSecao(ler.nextInt());
			ler.nextLine();
			
			System.out.println("Informe a Zona desta votação: ");
			votacao.setZona(ler.nextInt());
			ler.nextLine();
			
			System.out.println("Informe o Número de Eleitor: ");
			votacao.setNumEleitor(ler.nextLong());
			ler.nextLine();
			
			for(Votacao v: eleicaoDao.getVotacao()) {
				if(v.getNumEleitor() == votacao.getNumEleitor()) {
					votado = true;
					System.out.println("Este Número de Eleitor já registrou seu voto nesta eleição!");
					break;
				}
			}
			
			for(CadastroEleitor cad: eleicaoDao.getEleitor()) {
				if(cad.getNumEleitor() == votacao.getNumEleitor()) {
					existe = true;
					break;
				}
			}
			
			if(!existe) {
				System.out.println("Não foi possível encontrar o número de eleitor informado, por favor, verifique o numero e digite novamente!");
			}
			
			if(!votado && existe) {
				if(verificaSecaoZona(votacao, 0)) {
					if(verificaSecaoZona(votacao, 1)) {
						verificado = true;
					}else {
						for(CadastroEleitor ce: eleicaoDao.getEleitor()) {
							if(ce.getNumEleitor() == votacao.getNumEleitor()) {
								System.out.println("Dirija-se para a zona " +ce.getZona());
								break;
							}
						}
					}
				}else {
					for(CadastroEleitor ce: eleicaoDao.getEleitor()) {
						if(ce.getNumEleitor() == votacao.getNumEleitor()) {
							System.out.println("Dirija-se para a zona " +ce.getZona()+ " na seção " +ce.getSecao());
							break;
						}
					}
				}
			}
		}
		
		if(verificado) {
			while(true) {
				System.out.println(" 1 - Votar em José \n 2 - Votar em Maria \n 3 - Votar em Branco");
				int candidato = ler.nextInt();
				if(candidato >= 1 && candidato <= 99) {
					votacao.setNumCandidato(candidato);
					break;
				}
			}
		}else {
			System.out.println("Número de Candidato Inválido, por favor digite novamente!");
		}
		eleicaoDao.saveVotacao(votacao);
	}
	
	public boolean verificaSecaoZona(Votacao votacao, int i) {
		boolean verificado = false;
		for(CadastroEleitor ce: eleicaoDao.getEleitor()) {
			if(ce.getNumEleitor() == votacao.getNumEleitor()) {
				if(i == 0) {
					if(ce.getSecao() == votacao.getSecao()) {
						verificado = true;
						break;
					}
				}
				if(i == 1) {
					if(ce.getZona() == votacao.getZona()) {
						verificado = true;
						break;
					}
				}
			}
		}
		return verificado;
	}
	
	public int[] geraApuracao() {
		int n = eleicaoDao.getVotacao().size();
		Votacao[] votacao = new Votacao[n];
		int[] apuracao = new int[100];
		
		for(int i = 0; i < n; i++) {
			votacao[i] = new Votacao();
		}
		
		int k = 0;
		
		for(Votacao v : eleicaoDao.getVotacao()) {
			votacao[k] = v;
			k++;
		}
		
		for(int i = 0; i < n; i++) {
			apuracao[votacao[i].getNumCandidato() - 1]++;
		}
		
		return apuracao;
	}
	
	public void primeiroColocado() {
		int[] apuracao = geraApuracao();
		
		if(apuracao[0] > apuracao[1]) {
			System.out.println("A eleição foi vencida pelo candidato de código 1, José com: " +apuracao[0]+ " votos.");
		}else if(apuracao[1] > apuracao[0]) {
			System.out.println("A eleição foi vencida pelo candidato de código 2, Maria com: " +apuracao[1]+ " votos.");
		}else {
			System.out.println("Houve um empate na eleição!");
		}
	}
	
	public void segundoColocado() {
		int[] apuracao = geraApuracao();
		
		if(apuracao[0] > apuracao[1]) {
			System.out.println("O segundo colocado foi a candidata de código 2, Maria com: " +apuracao[1]+ " votos.");
		}else if(apuracao[1] > apuracao[0]) {
			System.out.println("O segundo colocado foi o candidato de código 1, José com: " +apuracao[0]+ " votos.");
		}else {
			System.out.println("Houve um empate na eleição!");
		}
	}
	
	public void votosBrancos() {
		int[] apuracao = geraApuracao();
		
		System.out.println("Foram somados " +apuracao[2]+ " votos brancos nesta eleição.");
	}
	
	public void votosNulos() {
		int[] apuracao = geraApuracao();
		
		for(int i = 4; i < 100; i++){
			apuracao[3] += apuracao[i];
        }
		
		System.out.println("Foram somados " +apuracao[3]+ " votos nulos nesta eleição.");
	}
	
	public void consultarEleitores() {
		for(CadastroEleitor ce : eleicaoDao.getEleitor()) {
			System.out.println("Id: " +ce.getId()+ " \n Número de Eleitor: " +ce.getNumEleitor()+ " \n Nome: " +ce.getNomeEleitor()+ " \n Secao: " +ce.getSecao()+ " \n Zona: " +ce.getZona());
		}
	}
	
	public void consultarApuracao() {
		int[] apuracao = geraApuracao();
		
		for(int i = 4; i < 100; i++){
			apuracao[3] += apuracao[i];
        }
		
		if(apuracao[1] > apuracao[0]) {
			System.out.println("A eleição foi vencida pelo candidato de código 2, Maria com: " +apuracao[1]+ " votos.");
			System.out.println("O segundo colocado foi o candidato de código 1, José com: " +apuracao[0]+ " votos.");
		}else {
			System.out.println("A eleição foi vencida pelo candidato de código 1, José com: " +apuracao[0]+ " votos.");
			System.out.println("O segundo colocado foi a candidata de código 2, Maria com: " +apuracao[1]+ " votos.");
		}
		
		System.out.println("Foram somados " +apuracao[2]+ " votos brancos nesta eleição.");
		System.out.println("Foram somados " +apuracao[3]+ " votos nulos nesta eleição.");
	}
	
	public void atualizarCadastro() {
		System.out.println("Informe o ATUAL Nome Completo do eleitor: ");
        String nome = ler.nextLine();
        boolean encontrado = false;
        
        for (CadastroEleitor ce : eleicaoDao.getEleitor()) {
            if (ce.getNomeEleitor().equals(nome)) {
                encontrado = true;
                int opc = 0, j = 0;
                
                while (opc != 9) {
                    System.out.println("Informe o dado que deseja atualizar: \n 1 - Atualizar Nome  \n 2 - Alterar Seção  \n 9 - Sair");
                    opc = ler.nextInt();
                    ler.nextLine();
                    
                    switch (opc) {
                        case 1:
                            System.out.println("Informe o Nome Completo do eleitor: ");
                            ce.setNomeEleitor(ler.nextLine());
                            eleicaoDao.update(ce);
                            j++;
                            break;
                        case 2:
                            System.out.println("Informe o Numero da Seção nova: ");
                            ce.setSecao(ler.nextInt());
                            ler.nextLine();
                            eleicaoDao.update(ce);
                            j++;
                            break;
                        case 9:
                            if (j > 0) {
                                System.out.println("As alterações foram salvas com sucesso!");
                            }
                            break;
                        default:
                            System.out.println("Opção Inválida!");
                    }
                }
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Eleitor '" + nome + "' não encontrado! Por favor, verifique o nome e digite novamente!");
        }
	}
	
	public void pesquisarCandidato() {
		int[] apuracao = geraApuracao();
		System.out.println("Informe o Número do Candidato: ");
        int num = ler.nextInt();
        ler.nextLine();
        if(num > 0 && num < 3) {
	        for (Votacao v : eleicaoDao.getVotacao()) {
	            if (v.getNumCandidato() == num) {
	                System.out.println("O candidato de número '" +num+ "' recebeu " +apuracao[num-1]+ " votos.");
	                break;
	            }
	        }
        }else {
            System.out.println("Candidato de Número '" + num + "' não encontrado! Por favor, verifique o num e digite novamente!");
        }
	}
	
	public void pesquisarEleitor() {
		int opc = 0;
		String nome = " ";
		long num = 0;
		while(true) {
			System.out.println("Pesquisar Eleitor por: \n 1 - Número de Eleitor \n 2 - Nome Completo");
			opc = ler.nextInt();
			ler.nextLine();
			
			if(opc == 1 || opc == 2) {
				break;
			}else {
				System.out.println("Opção Inválida!");
			}
		}
		if(opc == 1) {
			System.out.println("Informe o Número de Eleitor: ");
			num = ler.nextInt();
			ler.nextLine();
		}
		if(opc == 2) {
			System.out.println("Informe o Nome Completo do eleitor: ");
			nome = ler.nextLine();
		}
		
        boolean encontrado = false;
        for (CadastroEleitor ce : eleicaoDao.getEleitor()) {
            if (ce.getNomeEleitor().equals(nome) || ce.getNumEleitor() == num) {
                System.out.println("Eleitor Encontrado!");
                System.out.println("Id: " +ce.getId()+ " \n Número de Eleitor: " +ce.getNumEleitor()+ " \n Nome: " +ce.getNomeEleitor()+ " \n Secao: " +ce.getSecao()+ " \n Zona: " +ce.getZona());
                encontrado = true;
                break;
            }
        }
		if(!encontrado && opc == 1) {
			System.out.println("Número de Eleitor '" + num + "' não encontrado! Por favor, verifique o número de eleitor e digite novamente!");
		}else if (!encontrado && opc == 2) {
            System.out.println("Eleitor '" + nome + "' não encontrado! Por favor, verifique o nome e digite novamente!");
        }
	}
}
