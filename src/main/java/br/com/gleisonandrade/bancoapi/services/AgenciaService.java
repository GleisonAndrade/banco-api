/**
 * 
 */
package br.com.gleisonandrade.bancoapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gleisonandrade.bancoapi.domain.Agencia;
import br.com.gleisonandrade.bancoapi.repositories.AgenciaRepository;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
@Service
public class AgenciaService extends GenericServiceImpl<Agencia, Long>{
	@Autowired
	private AgenciaRepository agenciaRepository;
	
	public AgenciaService(AgenciaRepository agenciaRepository) {
		super(agenciaRepository);
		this.agenciaRepository = agenciaRepository;
	}
}
