/**
 * 
 */
package br.com.gleisonandrade.bancoapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gleisonandrade.bancoapi.domain.Cliente;
import br.com.gleisonandrade.bancoapi.repositories.custom.ClienteRepositoryCustom;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long>, ClienteRepositoryCustom {
}
