package br.com.sistema.contacorrente.aplication;

import java.util.Scanner;

import br.com.sistema.contacorrente.methods.Metodos;

public class Main {
	public static void main(String[] args) throws Exception{
		Metodos m = new Metodos();
		Scanner ler = new Scanner(System.in);
		int opc = 0;
		
		while(opc != 9){
            System.out.println("Informe a opção desejada: \n 1 - Cadastrar Conta Corrente \n 2 - Cadastrar Movimento \n 3 - Consultar Saldo \n 4 - Consultar Moviemntação da Conta \n 5 - Excluir Cadastro \n 9 - Finalizar Programa");
            opc = ler.nextInt();
            ler.nextLine();
            switch(opc){
                case 1:
                    m.cadastrarConta();
                    break;
                case 2:
                    m.cadastrarMovimento(); 
                    break;
                case 3:
                	m.consultarSaldo();
                	break;
                case 4:
                	m.consultarMovimentacoes();
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
