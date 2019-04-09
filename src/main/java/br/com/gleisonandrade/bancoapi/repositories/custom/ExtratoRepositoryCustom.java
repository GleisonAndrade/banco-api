/**
 * 
 */
package br.com.gleisonandrade.bancoapi.repositories.custom;

import java.util.List;

import br.com.gleisonandrade.bancoapi.domain.Extrato;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public interface ExtratoRepositoryCustom {
	public List<Extrato> listarTodosPorContaId(Long id);
}
