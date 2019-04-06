/**
 * 
 */
package br.com.gleisonandrade.bancoapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gleisonandrade.bancoapi.domain.Extrato;
import br.com.gleisonandrade.bancoapi.repositories.ExtratoRepository;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
@Service
public class ExtratoService extends GenericServiceImpl<Extrato, Long>{
	@Autowired
	private ExtratoRepository extratoRepository;
	
	public ExtratoService(ExtratoRepository extratoRepository) {
		super(extratoRepository);
		this.extratoRepository = extratoRepository;
	}
}
