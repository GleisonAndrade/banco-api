/**
 * 
 */
package br.com.gleisonandrade.bancoapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.gleisonandrade.bancoapi.domain.Conta;
import br.com.gleisonandrade.bancoapi.domain.enuns.Perfil;
import br.com.gleisonandrade.bancoapi.security.UserDetailsImpl;
import br.com.gleisonandrade.bancoapi.services.exceptions.AuthorizationException;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */

@Service
public class UserService {
	
	@Autowired
	private ContaService contaService;
	
	public UserDetailsImpl getUserDetails() {
		try {
			return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
	
	public boolean validaClienteConta(Conta conta) {
		UserDetailsImpl user = getUserDetails();
		
		if (user == null || !user.hasRole(Perfil.ADMIN) && !conta.getCliente().equals(user.getCliente())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		return true;
	}

	public boolean validaClienteConta(Long key) {
		return validaClienteConta(contaService.buscar(key));		
	}

	public void validaClienteId(Long key) {
		UserDetailsImpl user = getUserDetails();

		if (user == null || !user.hasRole(Perfil.ADMIN) && !key.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}		
	}

	public void validaClienteCpf(String cpf) {
		UserDetailsImpl user = getUserDetails();

		if (user == null || !user.hasRole(Perfil.ADMIN) && !cpf.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}		
	}
	
	public boolean hasRole(Perfil perfil) {
		UserDetailsImpl user = getUserDetails();
		
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		return user.hasRole(perfil);
	}

}
