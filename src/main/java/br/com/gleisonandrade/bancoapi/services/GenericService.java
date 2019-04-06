/**
 * 
 */
package br.com.gleisonandrade.bancoapi.services;

import java.util.List;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public interface GenericService<E,K> {
    public E adicionar(E entity) ;
    public E salvarOuAtualizar(E entity) ;
    public E atualizar(E entity) ;
    public void remover(E entity);
    public E buscar(K key);
    public List<E> listarTodos();
}
