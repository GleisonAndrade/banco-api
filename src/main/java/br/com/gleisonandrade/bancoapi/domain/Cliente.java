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
public class Cliente implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String cpf;
	private List<Conta> contas;

	public Cliente() {
	}

	/**
	 * @param nome
	 * @param cpf
	 * @param contas
	 */
	public Cliente(String nome, String cpf, List<Conta> contas) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.contas = contas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

}
