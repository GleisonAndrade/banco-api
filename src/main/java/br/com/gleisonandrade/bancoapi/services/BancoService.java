/**
 * 
 */
package br.com.gleisonandrade.bancoapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.gleisonandrade.bancoapi.domain.Banco;
import br.com.gleisonandrade.bancoapi.dto.BancoDTO;
import br.com.gleisonandrade.bancoapi.repositories.BancoRepository;
import br.com.gleisonandrade.bancoapi.services.exceptions.IntegridadeDeDadosException;
import br.com.gleisonandrade.bancoapi.services.exceptions.ObjetoNaoEncontradoException;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
@Service
public class BancoService extends GenericServiceImpl<Banco, Long>{
	@Autowired
	private BancoRepository bancoRepository;
	
	public BancoService(BancoRepository bancoRepository) {
		super(bancoRepository);
		this.bancoRepository = bancoRepository;
	}
	
	@Override
	public Banco buscar(Long key) {
		Optional<Banco> banco = bancoRepository.findById(key);
		
		return banco.orElseThrow(() -> new ObjetoNaoEncontradoException(
				"Objeto não encontrado! Id: " + key + ", Tipo: " + Banco.class.getName()));
	}
	
	@Override
	public Banco atualizar(Banco novoBanco) {
		Banco banco = buscar(novoBanco.getId());
		novoBanco = atualizaDados(banco, novoBanco);
		return super.atualizar(novoBanco);
	}

	@Override
	protected Banco atualizaDados(Banco entity, Banco newEntity) {
		Banco bancoAtualizado = new Banco();
		
		bancoAtualizado.setId(entity.getId());
		bancoAtualizado.setNome(newEntity.getNome());
		
		return bancoAtualizado;
	}
	
	@Override
	public void remover(Long key) {
		buscar(key);
		
		try {
			bancoRepository.deleteById(key);
		}
		catch (DataIntegrityViolationException  e) {
			throw new IntegridadeDeDadosException("Não é possível excluir um banco que possuí agências!");
		}
	}
	
	public Banco converteDTOEmEntidade(BancoDTO dto) {
		return new Banco(dto.getId(), dto.getNome());
	}
}
