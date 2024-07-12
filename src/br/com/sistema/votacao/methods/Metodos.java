package br.com.sistema.votacao.methods;

import java.util.Scanner;

import br.com.sistema.votacao.dao.VotacaoDAO;
import br.com.sistema.votacao.models.Votacao;


public class Metodos {
	VotacaoDAO votacaoDao = new VotacaoDAO();
	Scanner ler = new Scanner(System.in);
	
	public void cadastrarVotacao() {
		Votacao votacao = new Votacao();
		System.out.println("Informe o número da seção: ");
		votacao.setNumSecao(ler.nextInt());
		ler.nextLine();
		System.out.println("Informe o número do candidato a ser votado: ");
		votacao.setNumCandidato(ler.nextInt());
		ler.nextLine();
		votacaoDao.save(votacao);
	}
	
	public Votacao[] criaVetorOrganizado() {
		int n = votacaoDao.getVotacao().size();
		Votacao[] votacoes = new Votacao[n];
		
		for(int i = 0; i < n; i++) {
			votacoes[i] = new Votacao();
		}
		int k = 0;
		
		for(Votacao v : votacaoDao.getVotacao()) {
			votacoes[k] = v;
			k++;
		}
		
		return votacoes;
	}
	
	public void classificarNumeroSecao() {
		Votacao[] votacoes = criaVetorOrganizado();
		int n = votacoes.length;
		
		for(int i = 0; i < n-1; i++) {
			for(int j = i+1; j < n; j++) {
				if(votacoes[i].getNumSecao() > votacoes[j].getNumSecao()) {
					Votacao aux = votacoes[i];
					votacoes[i] = votacoes[j];
					votacoes[j] = aux;
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			System.out.println("Seção: " +votacoes[i].getNumSecao()+ " \n Id: " +votacoes[i].getId()+ " \n Número do Candidato: " +votacoes[i].getNumCandidato());
		}
	}
	
	public void consultarRegistros() {
		for(Votacao v : votacaoDao.getVotacao()) {
			System.out.println("Id: " +v.getId()+ " \n Seção: " +v.getNumSecao()+ " \n Número do Candidato: " +v.getNumCandidato());
		}
	}
	
	public int[] contaRepeticao(Votacao[] v, int n, int opc) {
		int[] votacao = new int[n];
		
		if(opc == 0) {
			for(int i = 0; i < n; i++) {
				votacao[v[i].getNumSecao()]++;
			}
		}
		if(opc == 1) {
			for(int i = 0; i < n; i++) {
				votacao[v[i].getNumCandidato()]++;
			}
		}
		
		return votacao;
	}
	
	public void consultarMaiorMenorSecao() {
		Votacao[] votacoes = criaVetorOrganizado();
		int n = votacoes.length;
		int[] secao = contaRepeticao(votacoes, n, 0);
		
		for(int i = 0; i < n-1; i++) {
			for(int j = 0; j < n; j++) {
				if(secao[i] > secao[j]) {
					int aux = secao[i];
					secao[i] = secao[j];
					secao[j] = aux;
				}
			}
		}
		
		System.out.println("A Seção com o Maior índice de votação foi a Seção: " +secao[n]);
		System.out.println("A Seção com o Menor índice de votação foi a Seção: " +secao[0]);
	}
	
	public void consultarDezMaisVotados() {
		Votacao[] votacoes = criaVetorOrganizado();
		int n = votacoes.length;
		int[] candidato = contaRepeticao(votacoes, n, 1);
		
		for(int i = 0; i < n-1; i++) {
			for(int j = 0; j < n; j++) {
				if(candidato[i] < candidato[j]) {
					Votacao aux = votacoes[i];
					votacoes[i] = votacoes[j];
					votacoes[j] = aux;
					
					int votos = candidato[i];
					candidato[i] = candidato[j];
					candidato[j] = votos;
				}
			}
		}
		
		
		System.out.println("Os 10 candidatos mais votados foram: ");
		for(int i = 0; i < 10; i++) {
			System.out.println((i+1)+ " - Candidato de Número: " +votacoes[i].getNumCandidato()+ " Recebeu " +candidato[i]+ " votos.");
		}
	}
	
	public void pesquisarNumeroCandidato() {
		Votacao[] votacoes = criaVetorOrganizado();
		int n = votacoes.length;
		int[] votos = contaRepeticao(votacoes, n, 1);
		boolean encontrado = false;
		System.out.println("Informe o Número do Candidato: ");
		int candidato = ler.nextInt();
		ler.nextLine();
		
		for(int i = 0; i < n; i++) {
			if(votacoes[i].getNumCandidato() == candidato) {
				System.out.println("O Candidato de Número: " +votacoes[i].getNumCandidato()+ " Recebeu " +votos[i]+ " votos.");
				encontrado = true;
				break;
			}
		}
		if(!encontrado) {
			System.out.println("Não foi possível encontrar um candidato que tenha o número '" +candidato+"'. Por favor digite novamente.");
		}
	}
	
	public void pesquisarSecao() {
		Votacao[] votacoes = criaVetorOrganizado();
		int n = votacoes.length;
		int[] votos = contaRepeticao(votacoes, n, 0);
		boolean encontrado = false;
		System.out.println("Informe a Seção: ");
		int secao = ler.nextInt();
		ler.nextLine();
		
		for(int i = 0; i < n; i++) {
			if(votacoes[i].getNumSecao() == secao) {
				System.out.println("A Seção de número: " +votacoes[i].getNumSecao()+ " Registrou " +votos[i]+ " votos.");
				encontrado = true;
				break;
			}
		}
		if(!encontrado) {
			System.out.println("Não foi possível encontrar uma secao que tenha o número '" +secao+"'. Por favor digite novamente.");
		}
	}
}
