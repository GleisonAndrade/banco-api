/**
 * 
 */
package br.com.gleisonandrade.bancoapi.dto;

import java.io.Serializable;

import br.com.gleisonandrade.bancoapi.domain.Extrato;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public class ExtratoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String informacoes;

	public ExtratoDTO(Extrato extrato) {
		this.informacoes = extrato.getInformacoes();
	}

	public String getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}
	
	
}
