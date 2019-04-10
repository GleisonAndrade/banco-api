/**
 * 
 */
package br.com.gleisonandrade.bancoapi.repositories.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.gleisonandrade.bancoapi.repositories.custom.BancoRepositoryCustom;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public class BancoRepositoryCustomImpl implements BancoRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

}
