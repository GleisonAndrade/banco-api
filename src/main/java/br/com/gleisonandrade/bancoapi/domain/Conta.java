/**
 * 
 */
package br.com.gleisonandrade.bancoapi.domain;

import java.io.Serializable;
import java.util.List;

import br.com.gleisonandrade.bancoapi.domain.enuns.TipoDeConta;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public class Conta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String numero;
	private TipoDeConta tipo;
	private Cliente cliente;
	private Agencia agencia;
	private Double saldo;
	private List<Extrato> extratos;

	public Conta() {
	}

	/**
	 * @param numero
	 * @param tipo
	 * @param cliente
	 * @param agencia
	 * @param saldo
	 */
	public Conta(String numero, TipoDeConta tipo, Cliente cliente, Agencia agencia, Double saldo) {
		super();
		this.numero = numero;
		this.tipo = tipo;
		this.cliente = cliente;
		this.agencia = agencia;
		this.saldo = saldo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public TipoDeConta getTipo() {
		return tipo;
	}

	public void setTipo(TipoDeConta tipo) {
		this.tipo = tipo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public List<Extrato> getExtratos() {
		return extratos;
	}

	public void setExtratos(List<Extrato> extratos) {
		this.extratos = extratos;
	}

}
