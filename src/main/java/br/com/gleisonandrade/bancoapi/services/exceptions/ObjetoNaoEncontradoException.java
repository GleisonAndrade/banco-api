/**
 * 
 */
package br.com.gleisonandrade.bancoapi.services.exceptions;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public class ObjetoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ObjetoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ObjetoNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
