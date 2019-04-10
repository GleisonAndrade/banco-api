/**
 * 
 */
package br.com.gleisonandrade.bancoapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gleisonandrade.bancoapi.domain.Cliente;
import br.com.gleisonandrade.bancoapi.domain.enuns.Perfil;
import br.com.gleisonandrade.bancoapi.repositories.ClienteRepository;
import br.com.gleisonandrade.bancoapi.security.UserDetailsImpl;
import br.com.gleisonandrade.bancoapi.services.exceptions.AuthorizationException;
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
		Cliente clienteBuscado = null;

		try {
			clienteBuscado = buscarPorCpf(entity.getCpf());
		} catch (ObjetoNaoEncontradoException e) {
			clienteBuscado = super.salvar(entity);
		}

		return clienteBuscado;
	}

	@Override
	protected Cliente atualizaDados(Cliente entity, Cliente newEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente buscar(Long key) {
		UserDetailsImpl user = UserService.autenticar();

		if (user == null || !user.hasRole(Perfil.ADMIN) && !key.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		return super.buscar(key);
	}

	public Cliente buscarPorCpf(String cpf) {
		UserDetailsImpl user = UserService.autenticar();

		if (user == null || !user.hasRole(Perfil.ADMIN) && !cpf.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}

		Optional<Cliente> cliente = clienteRepository.buscarPorCpf(cpf);

		return cliente.orElseThrow(() -> new ObjetoNaoEncontradoException("Cliente não encontrado! CPF: " + cpf));
	}

}
