/**
 * 
 */
package br.com.gleisonandrade.bancoapi.resources.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public class ErroValidacao extends StandardError {

	private static final long serialVersionUID = 1L;
	private List<MensagemCampoValidacao> erros = new ArrayList<>();
	
	public ErroValidacao(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public void adicionaErro(String nome, String mensagem) {
		erros.add(new MensagemCampoValidacao(nome, mensagem));
	}

	public List<MensagemCampoValidacao> getErros() {
		return erros;
	}

	public void setErros(List<MensagemCampoValidacao> erros) {
		this.erros = erros;
	}

}