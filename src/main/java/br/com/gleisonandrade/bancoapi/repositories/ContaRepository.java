/**
 * 
 */
package br.com.gleisonandrade.bancoapi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gleisonandrade.bancoapi.domain.Cliente;
import br.com.gleisonandrade.bancoapi.domain.Conta;
import br.com.gleisonandrade.bancoapi.repositories.custom.ContaRepositoryCustom;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public interface ContaRepository extends JpaRepository<Conta, Long>, ContaRepositoryCustom {

	Page<Conta> findByCliente(Cliente cliente, PageRequest pageRequest);
}
