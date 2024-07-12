package br.com.sistema.eleicao.models;

public class Votacao {
	private long id;
	private int secao;
	private int zona;
	private long numEleitor;
	private int numCandidato;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getSecao() {
		return secao;
	}
	public void setSecao(int secao) {
		this.secao = secao;
	}
	public int getZona() {
		return zona;
	}
	public void setZona(int zona) {
		this.zona = zona;
	}
	public long getNumEleitor() {
		return numEleitor;
	}
	public void setNumEleitor(long numEleitor) {
		this.numEleitor = numEleitor;
	}
	public int getNumCandidato() {
		return numCandidato;
	}
	public void setNumCandidato(int numCandidato) {
		this.numCandidato = numCandidato;
	}
}