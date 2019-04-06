package br.com.gleisonandrade.bancoapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class GenericServiceImpl<E, K> implements GenericService<E, K> {

	private JpaRepository<E, K> jpaRepository;

	public GenericServiceImpl(JpaRepository<E, K> jpaRepository) {
		this.jpaRepository = jpaRepository;
	}

	public GenericServiceImpl() {
	}
	
	@Override
	public E adicionar(E entity) {
		return jpaRepository.save(entity);
	}
	
	@Override
	public E salvarOuAtualizar(E entity) {
		return jpaRepository.save(entity);
	}
	
	@Override
	public E atualizar(E entity) {
		return jpaRepository.save(entity);
	}
	
	@Override
	public E buscar(K key) {
		Optional<E> obj = jpaRepository.findById(key);
		return obj.orElse(null);
	}
	
	@Override
	public void remover(E entity) {
		jpaRepository.delete(entity);
	}
	
	@Override
	public List<E> listarTodos() {
		return jpaRepository.findAll();
	}
	

}