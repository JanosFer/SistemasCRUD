package br.com.sistema.eleicao.aplication;

import java.util.Scanner;

import br.com.sistema.eleicao.methods.Metodos;

public class Main {
	public static void main(String[] args) throws Exception{
		Metodos m = new Metodos();
		Scanner ler = new Scanner(System.in);
		int opc = 0, opc3 = 0;
		
		while(opc != 9){
            System.out.println("Informe a opção desejada: \n 1 - Cadastrar Eleitor \n 2 - Cadastrar Votações \n 3 - Menu de Estatísticas \n 4 - Atualizar Cadastro \n 5 - Pesquisar Candidato \n 6 - Pesquisar Eleitor \n 9 - Finalizar Programa");
            opc = ler.nextInt();
            ler.nextLine();
            switch(opc){
                case 1:
                    m.cadastrarEleitor();
                    break;
                case 2:
                    m.cadastrarVotacoes(); 
                    break;
                case 3:
                    while(opc3 != 8){
                        System.out.println("Informe a opção desejada: \n 1 - Exibir Vencedor \n 2 - Exibir Segundo Colocado \n 3 - Consultar votos em Branco \n 4 - Consultar votos Nulos \n 5 - Consultar Eleitores \n 6 - Consultar Apuração \n 8 - Voltar ao menu anterior \n 9 - Finalizar programa");
                        opc3 = ler.nextInt();
                        ler.nextLine();
                        switch(opc3){
                            case 1:
                                m.primeiroColocado();
                                break;
                            case 2:
                                m.segundoColocado();
                                break;
                            case 3:
                                m.votosBrancos();
                                break;
                            case 4:
                                m.votosNulos();
                                break;
                            case 5:
                                m.consultarEleitores();
                                break;
                            case 6:
                                m.consultarApuracao();
                                break;
                            case 8:
                                break;
                            case 9:
                            	System.out.println("Programa Finalizado!");
                                opc = 9;
                                opc3 = 8;
                                break;
                            default:
                            	System.out.println("Opção Inválida!");
                        }
                    }
                    break;
                case 4:
                	m.atualizarCadastro();
                	break;
                case 5:
                	m.pesquisarCandidato();
                	break;
                case 6:
                	m.pesquisarEleitor();
                	break;
                case 9:
                	System.out.println("Programa Finalizado!");
                    break;
                default:
                	System.out.println("Opção Inválida!");
            }
        }
		ler.close();
	}
}
