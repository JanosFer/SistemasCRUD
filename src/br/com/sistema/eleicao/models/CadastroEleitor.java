package br.com.sistema.eleicao.models;

public class CadastroEleitor {
	private long id;
	private long numEleitor;
	private String nomeEleitor;
	private int secao;
	private int zona;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getNumEleitor() {
		return numEleitor;
	}
	public void setNumEleitor(long numEleitor) {
		this.numEleitor = numEleitor;
	}
	public String getNomeEleitor() {
		return nomeEleitor;
	}
	public void setNomeEleitor(String nomeEleitor) {
		this.nomeEleitor = nomeEleitor;
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
}
