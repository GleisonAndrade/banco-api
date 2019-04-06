/**
 * 
 */
package br.com.gleisonandrade.bancoapi.services.exceptions;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public class IntegridadeDeDadosException  extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public IntegridadeDeDadosException(String mensagem) {
		super(mensagem);
	}
	
	public IntegridadeDeDadosException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
