/**
 * 
 */
package br.com.gleisonandrade.bancoapi.repositories.custom;

import java.util.Optional;

import br.com.gleisonandrade.bancoapi.domain.Conta;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public interface ContaRepositoryCustom {
	public Optional<Conta> buscarPorNumero(String numero);
	public Optional<Conta> buscarPorNumeroAgenciaNumero(String contaNumero, String agenciaNumero);
	public Optional<Conta> buscarPorNumeroAgenciaNumeroBancoId(String contaNumero, String agenciaNumero, Long bancoId);
}
