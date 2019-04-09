/**
 * 
 */
package br.com.gleisonandrade.bancoapi.dto;

import java.io.Serializable;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
