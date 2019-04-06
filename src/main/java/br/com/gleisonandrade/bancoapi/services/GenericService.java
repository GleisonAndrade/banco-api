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
    public E salvar(E entity) ;
    public E atualizar(E entity) ;
    public void remover(K key);
    public E buscar(K key);
    public List<E> listarTodos();
}
