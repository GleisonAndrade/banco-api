/**
 * 
 */
package br.com.gleisonandrade.bancoapi.domain.enuns;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public enum TipoDeConta {
	
	CORRENTE("Conta corrente"), POUPANCA("Conta poupança");
	
	private String descricao;
	
	TipoDeConta(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}