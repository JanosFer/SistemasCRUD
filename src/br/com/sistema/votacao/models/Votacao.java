package br.com.sistema.votacao.models;

public class Votacao {
	private long id;
	private int numSecao;
	private int numCandidato;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getNumSecao() {
		return numSecao;
	}
	public void setNumSecao(int numSecao) {
		this.numSecao = numSecao;
	}
	public int getNumCandidato() {
		return numCandidato;
	}
	public void setNumCandidato(int numCandidato) {
		this.numCandidato = numCandidato;
	}
}
