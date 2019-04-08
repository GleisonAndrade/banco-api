/**
 * 
 */
package br.com.gleisonandrade.bancoapi.repositories.impl;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.gleisonandrade.bancoapi.domain.Conta;
import br.com.gleisonandrade.bancoapi.repositories.custom.ContaRepositoryCustom;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public class ContaRepositoryCustomImpl implements ContaRepositoryCustom {

	@Autowired
	private EntityManager entityManager;

	@Override
	public Optional<Conta> buscarPorNumero(String numero) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Conta> cq = cb.createQuery(Conta.class);

		Root<Conta> root = cq.from(Conta.class);
		CriteriaQuery<Conta> query = cq.select(root);

		Predicate predicado = cb.equal(root.get("numero"), numero);

		Predicate[] predicates = { predicado };

		query.where(predicates);

		TypedQuery<Conta> tq = entityManager.createQuery(query);

		try {
			return Optional.of(tq.getSingleResult());
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Optional<Conta> buscarPorNumeroAgenciaNumero(String contaNumero, String agenciaNumero) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Conta> cq = cb.createQuery(Conta.class);

		Root<Conta> root = cq.from(Conta.class);
		CriteriaQuery<Conta> query = cq.select(root);

		Predicate predicadoNumeroAgencia = cb.equal(root.get("agencia").get("numero"), agenciaNumero);
		Predicate predicado = cb.equal(root.get("numero"), contaNumero);

		Predicate[] predicates = { predicadoNumeroAgencia, predicado };

		query.where(predicates);

		TypedQuery<Conta> tq = entityManager.createQuery(query);

		try {
			return Optional.of(tq.getSingleResult());
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Optional<Conta> buscarPorNumeroAgenciaNumeroBancoId(String contaNumero, String agenciaNumero, Long bancoId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Conta> cq = cb.createQuery(Conta.class);

		Root<Conta> root = cq.from(Conta.class);
		CriteriaQuery<Conta> query = cq.select(root);

		Predicate predicadoNumeroAgencia = cb.equal(root.get("agencia").get("numero"), agenciaNumero);
		Predicate predicadoBancoId = cb.equal(root.get("agencia").get("banco").get("id"), bancoId);
		Predicate predicado = cb.equal(root.get("numero"), contaNumero);

		Predicate[] predicates = { predicadoNumeroAgencia, predicado, predicadoBancoId };

		query.where(predicates);

		TypedQuery<Conta> tq = entityManager.createQuery(query);

		try {
			return Optional.of(tq.getSingleResult());
		} catch (NoResultException e) {
			return null;
		}
	}

}
