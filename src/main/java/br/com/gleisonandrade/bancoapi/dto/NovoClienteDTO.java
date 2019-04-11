/**
 * 
 */
package br.com.gleisonandrade.bancoapi.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
@ApiModel(description="Detalhes sobre o cliente para cadastro. ")
public class NovoClienteDTO {
	
	@ApiModelProperty(notes = "Nome do cliente. ")
	@NotEmpty(message="Preenchimento obrigatório")
	private String nome;
	
	@ApiModelProperty(notes = "Cpf do cliente. ")
	@NotEmpty(message="Preenchimento obrigatório")
	@CPF(message="O CPF informado é inválido")
	private String cpf;
	
	@ApiModelProperty(notes = "Senha do cliente. ")
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