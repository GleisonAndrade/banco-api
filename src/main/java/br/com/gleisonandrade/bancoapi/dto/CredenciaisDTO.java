package br.com.gleisonandrade.bancoapi.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
@ApiModel(description="Dados necessário para acesso ao sistema. ")
public class CredenciaisDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(notes = "Cpf do cliente. ")
	private String cpf;
	
	@ApiModelProperty(notes = "Senha do cliente. ")
	private String senha;
	
	public CredenciaisDTO() {
		
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