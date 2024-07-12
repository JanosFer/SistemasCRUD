package br.com.sistema.contacorrente.models;

import java.util.Date;

public class Movimentacoes {
	private long id;
	private int cod;
	private double valorMovimento;
	private int tipoMovimento;
	private Date dataMovimentacao;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public double getValorMovimento() {
		return valorMovimento;
	}
	public void setValorMovimento(double valorMovimento) {
		this.valorMovimento = valorMovimento;
	}
	public int getTipoMovimento() {
		return tipoMovimento;
	}
	public void setTipoMovimento(int tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}
	public Date getDataMovimentacao() {
		return dataMovimentacao;
	}
	public void setDataMovimentacao(Date dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}
}
