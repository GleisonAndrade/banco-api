/**
 * 
 */
package br.com.gleisonandrade.bancoapi.resources.exception;

import java.io.Serializable;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public class MensagemCampoValidacao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String mensagem;
	
	public MensagemCampoValidacao() {
	}

	/**
	 * @param nome
	 * @param mensagem
	 */
	public MensagemCampoValidacao(String nome, String mensagem) {
		super();
		this.nome = nome;
		this.mensagem = mensagem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}