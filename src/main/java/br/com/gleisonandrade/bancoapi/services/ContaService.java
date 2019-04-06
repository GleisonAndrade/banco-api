/**
 * 
 */
package br.com.gleisonandrade.bancoapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gleisonandrade.bancoapi.domain.Conta;
import br.com.gleisonandrade.bancoapi.repositories.ContaRepository;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
@Service
public class ContaService extends GenericServiceImpl<Conta, Long>{
	@Autowired
	private ContaRepository contaRepository;
	
	public ContaService(ContaRepository contaRepository) {
		super(contaRepository);
		this.contaRepository = contaRepository;
	}
}
