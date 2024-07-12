package br.com.sistema.transito.methods;

import java.util.Scanner;
import br.com.sistema.transito.dao.TransitoDAO;
import br.com.sistema.transito.models.Transito;

public class Metodos {
	TransitoDAO transitoDao = new TransitoDAO();
	Scanner ler = new Scanner(System.in);
	
	public void cadastrarAcidente() {
		Transito transito = new Transito();
		System.out.println("Informe o Nome da Cidade: ");
		transito.setNomeCidade(ler.nextLine());
		System.out.println("Informe a Quantidade de Acidentes: ");
		transito.setQtdAcidentes(ler.nextInt());
		ler.nextLine();
		transitoDao.save(transito);
	}
	
	public void consultarRegistros() {
		for(Transito t : transitoDao.getTransito()) {
			System.out.println("Id: " +t.getId()+ " \n Cidade: " +t.getNomeCidade()+ " \n Quantidade de Acidentes: " +t.getQtdAcidentes());
		}
	}
	
	public Transito[] criaVetorOrganizado() {
		int n = transitoDao.getTransito().size();
		Transito[] cidades = new Transito[n];
		
		for(int i = 0; i < n; i++) {
			cidades[i] = new Transito();
		}
		int k = 0;
		
		for(Transito t : transitoDao.getTransito()) {
			cidades[k] = t;
			k++;
		}
		
		for(int i = 0; i < n-1; i++) {
			for(int j = (i+1); j < n; j++) {
				if(cidades[i].getQtdAcidentes() > cidades[j].getQtdAcidentes()) {
					Transito aux = cidades[i];
					cidades[i] = cidades[j];
					cidades[j] = aux;
				}
			}
		}
		
		return cidades;
	}
	
	public void consultarCemQuinhentos() {
		Transito[] cidades = criaVetorOrganizado();
		int n = cidades.length;
		
		System.out.println("As cidades que registraram entre 100 e 500 acidentes foram: ");
		
		for(int i = 0; i < n; i++) {
			if(cidades[i].getQtdAcidentes() >= 100 && cidades[i].getQtdAcidentes() <= 500) {
				System.out.println("Id: " +cidades[i].getId()+ " \n Cidade: " +cidades[i].getNomeCidade()+ " \n Quantidade de Acidentes: " +cidades[i].getQtdAcidentes());
			}
		}
	}
	
	public void consultarMaiorMenor() {
		Transito[] cidades = criaVetorOrganizado();
		int n = cidades.length;
		
		System.out.println("A cidade com o maior índice de acidentes foi " +cidades[n-1].getNomeCidade()+ " com " +cidades[n-1].getQtdAcidentes()+ " acidentes.");
		System.out.println("A cidade com o menor índice de acidentes foi " +cidades[0].getNomeCidade()+ " com " +cidades[0].getQtdAcidentes()+ " acidentes.");
	}
	
	public void consultarAcimaMedia() {
		Transito[] cidades = criaVetorOrganizado();
		int n = cidades.length;
		int soma = 0;
		
		System.out.println("As cidades com índice de acidentes acima da média foram: ");
		
		for(int i = 0; i < n; i++) {
			soma += cidades[i].getQtdAcidentes();
		}
		double media = soma/n;
		
		for(int i = 0; i < n; i++) {
			if(cidades[i].getQtdAcidentes() > media) {
				System.out.println("Id: " +cidades[i].getId()+ " \n Cidade: " +cidades[i].getNomeCidade()+ " \n Quantidade de Acidentes: " +cidades[i].getQtdAcidentes());
			}
		}
		
	}
	
	public void pesquisarCidade() {
		Transito[] cidades = criaVetorOrganizado();
		int n = cidades.length;
		boolean encontrado = false;
		System.out.println("Informe o Nome da Cidade: ");
		String nome = ler.nextLine();
		
		for(int i = 0; i < n; i++) {
			if(cidades[i].getNomeCidade().equals(nome)) {
				System.out.println("Id: " +cidades[i].getId()+ " \n Cidade: " +cidades[i].getNomeCidade()+ " \n Quantidade de Acidentes: " +cidades[i].getQtdAcidentes());
				encontrado = true;
				break;
			}
		}
		if(!encontrado) {
			System.out.println("Cidade '"+nome+"' não encontrada, por favor verifique o nome e digite novamente!");
		}
	}
	
	public void atualizarDados() {
		Transito[] cidades = criaVetorOrganizado();
		int n = cidades.length;
		boolean encontrado = false;
		System.out.println("Informe o Nome da Cidade: ");
		String nome = ler.nextLine();
		
		for(int i = 0; i < n; i++) {
			if(cidades[i].getNomeCidade().equals(nome)) {
				encontrado = true;
				int opc = 0, j = 0;
				
				while(opc != 9) {
					System.out.println("Informe o dado que deseja atualizar: \n 1 - Atualizar Nome da Cidade  \n 2 - Atualizar Quantidade de Acidentes  \n 9 - Sair");
					opc = ler.nextInt();
					ler.nextLine();
				
					switch(opc) {
						case 1:
							System.out.println("Informe o Nome da Cidade: ");
							cidades[i].setNomeCidade(ler.nextLine());
							transitoDao.update(cidades[i]);
							j++;
							break;
						case 2:
							System.out.println("Informe a Quantidade de Acidentes: ");
							cidades[i].setQtdAcidentes(ler.nextInt());
							ler.nextLine();
							transitoDao.update(cidades[i]);
							j++;
							break;
						case 9:
							if(j > 0) {
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
            System.out.println("Cidade '" + nome + "' não encontrada! Por favor, verifique o nome e digite novamente!");
        }
	}
	
	public void excluirCadastro() {
		Transito[] cidades = criaVetorOrganizado();
		int n = cidades.length;
		boolean encontrado = false;
		System.out.println("Informe o Nome da Cidade: ");
		String nome = ler.nextLine();
		
		for(int i = 0; i < n; i++) {
			if(cidades[i].getNomeCidade().equals(nome)) {
				encontrado = true;
				int opc = 0, fim = 0;
				System.out.println("Deseja realmente excluir o cadastro da cidade '" +nome+ "'? \n Essa ação não poderá ser alterada futuramente!");
				while (fim != 9) {
					System.out.println("Digite 1 para continuar e 2 para cancelar");
					opc = ler.nextInt();
					ler.nextLine();
					switch(opc) {
						case 1:
							System.out.println("O cadastro da cidade '" +nome+ "' foi EXCLUÍDO com sucesso.");
							transitoDao.deleteById(cidades[i].getId());
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
		if (!encontrado) {
            System.out.println("Cidade '" + nome + "' não encontrada! Por favor, verifique o nome e digite novamente!");
        }
	}
}
