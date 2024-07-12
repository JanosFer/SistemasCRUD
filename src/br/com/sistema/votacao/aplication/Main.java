package br.com.sistema.votacao.aplication;

import java.util.Scanner;

import br.com.sistema.votacao.methods.Metodos;

public class Main {
	public static void main(String[] args) throws Exception{
		Metodos m = new Metodos();
		Scanner ler = new Scanner(System.in);
		int opc = 0, opc4 = 0;
		
		while(opc != 9) {
			System.out.println("Informe a opção desejada: \n Digite: \n 1 - Cadastrar Votação \n 2 - Classificar por Número de Seção \n 3 - Consultar Todos os Registros \n 4 - Consultar Estatisticas \n 9 - Finalizar programa");
			opc = ler.nextInt();
			ler.nextLine();
			switch(opc) {
				case 1:
					m.cadastrarVotacao();
					break;
				case 2:
					m.classificarNumeroSecao();
					break;
				case 3:
					m.consultarRegistros();
					break;
				case 4:
					while(opc4 != 8){
						System.out.println("Informe a opção desejada: \n 1 - Quantidade de eleitores por eleição. \n 2 - Seção com maior e menor número de eleitores. \n 3 - Quantidade de votos por candidato. \n 4 - 10 candidatos mais votados. \n 8 - Voltar ao menu anterior. \n 9 - Finalizar programa.");
						opc4 = ler.nextInt();
						ler.nextLine();
						switch(opc4) {
						case 1:
                            m.pesquisarSecao();
                            break;
                        case 2:
                            m.consultarMaiorMenorSecao();
                            break;
                        case 3:
                            m.pesquisarNumeroCandidato();
                            break;
                        case 4:
                            m.consultarDezMaisVotados();
                            break;
                        case 8:
                            break;
                        case 9:
                        	System.out.println("Programa Finalizado!");
                            opc4 = 8;
                            opc = 9;
                            break;
                        default:
                        	System.out.println("Opção Inválida!");
						}
					}
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
