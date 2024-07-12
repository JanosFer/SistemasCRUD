package br.com.sistema.alunos.aplication;

import java.util.Scanner;

import br.com.sistema.alunos.methods.Metodos;

public class Main {
	public static void main (String[] args) throws Exception {
		Metodos m = new Metodos();
		Scanner ler = new Scanner(System.in);
		
		int opc = 0;
		
		while(opc != 9) {
			System.out.println("Informe a opção desejada: \n 1 - Cadastrar Alunos \n 2 - Consultar todos os registros \n 3 - Pesquisar Aluno \n 4 - Atualizar Dados \n 5 - Excluir Cadastro \n 9 - Finalizar");
			opc = ler.nextInt();
			
			switch(opc) {
				case 1:
					m.cadastrarAluno();
					break;
				case 2:
					m.consultarAlunos();
					break;
				case 3:
					m.pesquisarAluno();
					break;
				case 4:
					m.atualizarDados();
					break;
				case 5:
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
