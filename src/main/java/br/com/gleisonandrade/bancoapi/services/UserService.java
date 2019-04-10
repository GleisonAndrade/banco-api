/**
 * 
 */
package br.com.gleisonandrade.bancoapi.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.gleisonandrade.bancoapi.security.UserDetailsImpl;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public class UserService {
	public static UserDetailsImpl autenticar() {
		try {
			return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
}
