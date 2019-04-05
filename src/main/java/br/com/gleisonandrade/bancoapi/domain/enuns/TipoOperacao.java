package br.com.gleisonandrade.bancoapi.domain.enuns;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public enum TipoOperacao {
	DEPOSITO("Deposito"), SAQUE("Saque"), TRANSFERENCIA("Transferência");
	
	private String descricao;
	
	TipoOperacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
