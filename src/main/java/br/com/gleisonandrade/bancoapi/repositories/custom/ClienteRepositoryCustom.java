/**
 * 
 */
package br.com.gleisonandrade.bancoapi.repositories.custom;

import java.util.Optional;

import br.com.gleisonandrade.bancoapi.domain.Cliente;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public interface ClienteRepositoryCustom {
	public Optional<Cliente> buscarPorCpf(String cpf);
}
