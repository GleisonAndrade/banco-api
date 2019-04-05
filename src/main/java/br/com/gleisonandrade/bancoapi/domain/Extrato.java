/**
 * 
 */
package br.com.gleisonandrade.bancoapi.domain;

import java.io.Serializable;
import java.util.Calendar;

import br.com.gleisonandrade.bancoapi.domain.enuns.TipoOperacao;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public class Extrato implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Calendar data;
	private TipoOperacao tipoOperacao;
	private Double valor;
	private Conta conta;
	private String informacoes;

	/**
	 * 
	 */
	public Extrato() {
	}

	/**
	 * @param data
	 * @param tipoOperacao
	 * @param valor
	 * @param conta
	 */
	public Extrato(Calendar data, TipoOperacao tipoOperacao, Double valor, Conta conta) {
		super();
		this.data = data;
		this.tipoOperacao = tipoOperacao;
		this.valor = valor;
		this.conta = conta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public TipoOperacao getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(TipoOperacao tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public String getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}

}
