/**
 * 
 */
package br.com.gleisonandrade.bancoapi.repositories.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.gleisonandrade.bancoapi.domain.Agencia;
import br.com.gleisonandrade.bancoapi.repositories.custom.AgenciaRepositoryCustom;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public class AgenciaRepositoryCustomImpl implements AgenciaRepositoryCustom {

	@Autowired
	private EntityManager entityManager;

	@Override
	public Optional<Agencia> buscarPorNumero(Long bancoId, String numeroDaConta) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Agencia> cq = cb.createQuery(Agencia.class);

		Root<Agencia> root = cq.from(Agencia.class);
		CriteriaQuery<Agencia> query = cq.select(root);

		Predicate predicadoBancoId = cb.equal(root.get("banco").get("id"), bancoId);
		Predicate predicado = cb.equal(root.get("numero"), numeroDaConta);

		Predicate[] predicates = { predicadoBancoId, predicado };

		query.where(predicates);

		TypedQuery<Agencia> tq = entityManager.createQuery(query);

		try {
			return Optional.of(tq.getSingleResult());
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Agencia> buscarPorNumero(Long id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Agencia> cq = cb.createQuery(Agencia.class);

		Root<Agencia> root = cq.from(Agencia.class);
		CriteriaQuery<Agencia> query = cq.select(root);

		Predicate predicadoBancoId = cb.equal(root.get("banco").get("id"), id);
		query.where(predicadoBancoId);

		TypedQuery<Agencia> tq = entityManager.createQuery(query);

		return tq.getResultList();
	}

}
