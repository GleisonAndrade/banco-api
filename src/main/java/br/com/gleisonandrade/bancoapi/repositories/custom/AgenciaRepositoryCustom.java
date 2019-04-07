/**
 * 
 */
package br.com.gleisonandrade.bancoapi.repositories.custom;

import java.util.List;
import java.util.Optional;

import br.com.gleisonandrade.bancoapi.domain.Agencia;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public interface AgenciaRepositoryCustom {
	public Optional<Agencia> buscarPorNumero(Long bancoId, String numeroDaConta);
	public List<Agencia> buscarPorNumero(Long id);
}
