/**
 * 
 */
package br.com.gleisonandrade.bancoapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gleisonandrade.bancoapi.domain.Banco;
import br.com.gleisonandrade.bancoapi.repositories.BancoRepository;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
@Service
public class BancoService extends GenericServiceImpl<Banco, Long>{
	@Autowired
	private BancoRepository bancoRepository;
	
	public BancoService(BancoRepository bancoRepository) {
		super(bancoRepository);
		this.bancoRepository = bancoRepository;
	}
}
