/**
 * 
 */
package br.com.gleisonandrade.bancoapi.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public class Agencia implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String numero;
	private String nome;
	private Banco banco;
	private List<Conta> contas;

	public Agencia() {
	}

	/**
	 * @param numero
	 * @param nome
	 * @param banco
	 * @param contas
	 */
	public Agencia(String numero, String nome, Banco banco, List<Conta> contas) {
		super();
		this.numero = numero;
		this.nome = nome;
		this.banco = banco;
		this.contas = contas;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}
}