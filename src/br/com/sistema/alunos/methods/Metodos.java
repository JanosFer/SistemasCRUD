package br.com.sistema.alunos.methods;

import java.util.Scanner;
import br.com.sistema.alunos.dao.AlunoDAO;
import br.com.sistema.alunos.models.Aluno;

public class Metodos {
    AlunoDAO alunoDao = new AlunoDAO();
    Scanner ler = new Scanner(System.in);
    
    public void cadastrarAluno() {
        Aluno aluno = new Aluno();
        System.out.println("Informe o Nome do Aluno: ");
        aluno.setNome(ler.nextLine());
        aluno.setPontos(0.0);
        alunoDao.save(aluno);
    }
    
    public void consultarAlunos() {
        for (Aluno a : alunoDao.getAlunos()) {
            System.out.println("Id: " + a.getId() + " \n Nome Completo: " + a.getNome() + " \n Pontos: " + a.getPontos());
        }
    }
    
    public void pesquisarAluno() {
        System.out.println("Informe o Nome Completo do aluno: ");
        String nome = ler.nextLine();
        boolean encontrado = false;
        for (Aluno a : alunoDao.getAlunos()) {
            if (a.getNome().equals(nome)) {
                System.out.println("Aluno Encontrado!");
                System.out.println("Id: " + a.getId() + " \n Nome Completo: " + a.getNome() + " \n Pontos: " + a.getPontos());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Aluno '" + nome + "' não encontrado! Por favor, verifique o nome e digite novamente!");
        }
    }
    
    public void atualizarDados() {
        System.out.println("Informe o ATUAL Nome Completo do aluno: ");
        String nome = ler.nextLine();
        boolean encontrado = false;
        
        for (Aluno a : alunoDao.getAlunos()) {
            if (a.getNome().equals(nome)) {
                encontrado = true;
                int opc = 0, i = 0;
                
                while (opc != 9) {
                    System.out.println("Informe o dado que deseja atualizar: \n 1 - Atualizar Nome  \n 2 - Atualizar Pontos  \n 9 - Sair");
                    opc = ler.nextInt();
                    ler.nextLine();
                    
                    switch (opc) {
                        case 1:
                            System.out.println("Informe o Nome Completo do aluno");
                            a.setNome(ler.nextLine());
                            alunoDao.update(a);
                            i++;
                            break;
                        case 2:
                            System.out.println("Informe o Numero de Pontos do Aluno");
                            a.setPontos(ler.nextDouble());
                            ler.nextLine();
                            alunoDao.update(a);
                            i++;
                            break;
                        case 9:
                            if (i > 0) {
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
            System.out.println("Aluno '" + nome + "' não encontrado! Por favor, verifique o nome e digite novamente!");
        }
    }
    
    public void excluirCadastro() {
        System.out.println("Informe o ATUAL Nome Completo do aluno: ");
        String nome = ler.nextLine();
        boolean encontrado = false;
        
        for (Aluno a : alunoDao.getAlunos()) {
            if (a.getNome().equals(nome)) {
                encontrado = true;
                int opc = 0, fim = 0;
                
                System.out.println("Deseja realmente excluir o cadastro do aluno '" +nome+ "'? \n Essa ação não poderá ser alterada futuramente!");
				while(fim != 9) {
					System.out.println("Digite 1 para continuar e 2 para cancelar");
					opc = ler.nextInt();	
					ler.nextLine();
					switch (opc) {
					case 1:
						System.out.println("O cadastro do aluno '" +nome+ "' foi EXCLUÍDO com sucesso.");
						alunoDao.deleteByID(a.getId());
						fim = 9;
						break;
					case 2:
						System.out.println("O cadastro foi MANTIDO.");
						fim = 9;
						break;
					default:
						System.out.println("Opção Inválida!");
					}
				}
			} 
		}
        if(!encontrado) {
			System.out.println("Aluno '" +nome+ "' não encontrado! Por favor, verifique o nome e digite novamente!");
		}
	}
}
