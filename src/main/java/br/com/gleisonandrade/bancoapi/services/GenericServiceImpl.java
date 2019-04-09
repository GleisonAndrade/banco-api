package br.com.gleisonandrade.bancoapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.gleisonandrade.bancoapi.domain.Banco;

@Service
public abstract class GenericServiceImpl<E, K> implements GenericService<E, K> {

	private JpaRepository<E, K> jpaRepository;

	public GenericServiceImpl(JpaRepository<E, K> jpaRepository) {
		this.jpaRepository = jpaRepository;
	}

	public GenericServiceImpl() {
	}
	
	/**
	 * Define que dados devem ser atualizados da entidade.
	 * 
	 * @param entity entidade que será atualizada.
	 * @param newEntity entidade com os novos dados a serem copiados.
	 * @return uma nova instância da entidade com os dados atualizados.
	 */
	protected abstract E atualizaDados(E entity, E newEntity); 
	
	/**
	 * Salva a entidade passada e retorna a entidade atualizada e com identificador definido.
	 */
	@Override
	public E salvar(E entity) {
		return jpaRepository.save(entity);
	}
	
	/**
	 * Atualiza os dados de uma entidade de forma simples. Apenas pega os dados da entidade e atualiza no banco.
	 */
	@Override
	public E atualizar(E entity) {
		return jpaRepository.save(entity);
	}
	
	/**
	 * Busca uma entidade pela sua chave. Caso ela não exista é retornado um objeto null.
	 */
	@Override
	public E buscar(K key) {
		Optional<E> obj = jpaRepository.findById(key);
		return obj.orElse(null);
	}
	
	/**
	 * Remove um entidade do banco de acordo com o id do objeto.
	 */
	@Override
	public void remover(K key) {
		jpaRepository.deleteById(key);
	}
	
	/**
	 * Lista todos os registros da entidade no banco.
	 */
	@Override
	public List<E> listarTodos() {
		return jpaRepository.findAll();
	}
	
	public Page<E> buscaPaginada(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return jpaRepository.findAll(pageRequest);
	}
	
	public List<E> saveAll(List<E> entities) {
		return jpaRepository.saveAll(entities);
	}
}