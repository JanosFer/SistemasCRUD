package br.com.sistema.transito.aplication;

import java.util.Scanner;

import br.com.sistema.transito.methods.Metodos;

public class Main {
	public static void main(String[] args) throws Exception{
		Metodos m = new Metodos();
		Scanner ler = new Scanner(System.in);
		int opc = 0;
		
		while(opc != 9) {
			System.out.println("Informe a opção desejada: \n 1 - Cadastrar Acidentes \n 2 - Consultar todos os registros \n 3 - Consultar cidades com mais de 100 acidentes e menos de 500 acidentes \n 4 - Consultar as cidades com o maior número de acidentes e com o menor número de acidentes \n 5 - Consultar cidades com número de acidentes acima da média \n 6 - Pesquisar Cidade \n 7 - Atualizar Dados \n 8 - Excluir Cadastro \n 9 - Finalizar");
			opc = ler.nextInt();
			switch(opc) {
			case 1:
				m.cadastrarAcidente();
				break;
			case 2:
				m.consultarRegistros();
				break;
			case 3:
				m.consultarCemQuinhentos();
				break;
			case 4:
				m.consultarMaiorMenor();
				break;
			case 5:
				m.consultarAcimaMedia();
				break;
			case 6:
				m.pesquisarCidade();
				break;
			case 7:
				m.atualizarDados();
				break;
			case 8:
				m.excluirCadastro();
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
