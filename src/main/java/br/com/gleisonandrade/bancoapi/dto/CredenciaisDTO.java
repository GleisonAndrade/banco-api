package br.com.gleisonandrade.bancoapi.dto;

import java.io.Serializable;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public class CredenciaisDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String cpf;
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