/**
 * 
 */
package br.com.gleisonandrade.bancoapi.util;

import java.io.Serializable;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public class ContaExtrato implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long contaId;
	private Long extratoId;
	
	/**
	 * @param contaId
	 * @param extratoId
	 */
	public ContaExtrato(Long contaId, Long extratoId) {
		super();
		this.contaId = contaId;
		this.extratoId = extratoId;
	}

	public Long getContaId() {
		return contaId;
	}

	public void setContaId(Long contaId) {
		this.contaId = contaId;
	}

	public Long getExtratoId() {
		return extratoId;
	}

	public void setExtratoId(Long extratoId) {
		this.extratoId = extratoId;
	}

}
