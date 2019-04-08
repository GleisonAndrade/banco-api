/**
 * 
 */
package br.com.gleisonandrade.bancoapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gleisonandrade.bancoapi.domain.Cliente;
import br.com.gleisonandrade.bancoapi.repositories.ClienteRepository;
import br.com.gleisonandrade.bancoapi.services.exceptions.ObjetoNaoEncontradoException;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
@Service
public class ClienteService extends GenericServiceImpl<Cliente, Long> {

	@Autowired
	private ClienteRepository clienteRepository;

	public ClienteService(ClienteRepository clienteRepository) {
		super(clienteRepository);
		this.clienteRepository = clienteRepository;
	}
	
	@Override
	public Cliente salvar(Cliente entity) {
		Cliente clienteBuscado = buscarPorCpf(entity.getCpf());
		
		if(clienteBuscado == null) {
			clienteBuscado = super.salvar(entity);
		}
		
		return clienteBuscado;
	}

	@Override
	protected Cliente atualizaDados(Cliente entity, Cliente newEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	public Cliente buscarPorCpf(String cpf) {
		Optional<Cliente> cliente = clienteRepository.buscarPorCpf(cpf);

		return cliente.orElseThrow(() -> new ObjetoNaoEncontradoException(
				"Cliente não encontrado! CPF: " + cpf));
	}

}
