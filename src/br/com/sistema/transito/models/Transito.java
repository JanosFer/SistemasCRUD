package br.com.sistema.transito.models;

public class Transito {
	private long id;
	private String nomeCidade;
	private int qtdAcidentes;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNomeCidade() {
		return nomeCidade;
	}
	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}
	public int getQtdAcidentes() {
		return qtdAcidentes;
	}
	public void setQtdAcidentes(int qtdAcidentes) {
		this.qtdAcidentes = qtdAcidentes;
	}
}
