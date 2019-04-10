/**
 * 
 */
package br.com.gleisonandrade.bancoapi.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public class NovoClienteDTO {
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String nome;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@CPF(message="O CPF informado é inválido")
	private String cpf;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String senha;
	
	/**
	 * @param nome
	 * @param cpf
	 * @param senha
	 * @param perfies
	 */
	public NovoClienteDTO(String nome, String cpf, String senha) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
		
}