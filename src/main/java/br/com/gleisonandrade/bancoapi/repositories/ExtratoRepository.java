/**
 * 
 */
package br.com.gleisonandrade.bancoapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gleisonandrade.bancoapi.domain.Extrato;
import br.com.gleisonandrade.bancoapi.repositories.custom.ExtratoRepositoryCustom;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public interface ExtratoRepository extends JpaRepository<Extrato, Long>, ExtratoRepositoryCustom {

}
