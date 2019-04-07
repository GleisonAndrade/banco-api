/**
 * 
 */
package br.com.gleisonandrade.bancoapi.repositories.custom;

import br.com.gleisonandrade.bancoapi.domain.Agencia;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public interface AgenciaRepositoryCustom {
	Agencia buscarPorNumero(String numero);
}
