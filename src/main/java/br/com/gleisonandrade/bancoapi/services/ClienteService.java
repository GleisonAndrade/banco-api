/**
 * 
 */
package br.com.gleisonandrade.bancoapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gleisonandrade.bancoapi.domain.Cliente;
import br.com.gleisonandrade.bancoapi.repositories.ClienteRepository;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
@Service
public class ClienteService extends GenericServiceImpl<Cliente, Long>{
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public ClienteService(ClienteRepository clienteRepository) {
		super(clienteRepository);
		this.clienteRepository = clienteRepository;
	}

	@Override
	protected Cliente atualizaDados(Cliente entity, Cliente newEntity) {
		// TODO Auto-generated method stub
		return null;
	}

}
