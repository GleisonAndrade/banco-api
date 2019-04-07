/**
 * 
 */
package br.com.gleisonandrade.bancoapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.gleisonandrade.bancoapi.domain.Agencia;
import br.com.gleisonandrade.bancoapi.domain.Banco;
import br.com.gleisonandrade.bancoapi.dto.AgenciaDTO;
import br.com.gleisonandrade.bancoapi.dto.NovaAgenciaDTO;
import br.com.gleisonandrade.bancoapi.repositories.AgenciaRepository;
import br.com.gleisonandrade.bancoapi.services.exceptions.IntegridadeDeDadosException;
import br.com.gleisonandrade.bancoapi.services.exceptions.ObjetoNaoEncontradoException;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
@Service
public class AgenciaService extends GenericServiceImpl<Agencia, Long>{
	@Autowired
	private AgenciaRepository agenciaRepository;
	
	public AgenciaService(AgenciaRepository agenciaRepository) {
		super(agenciaRepository);
		this.agenciaRepository = agenciaRepository;
	}

	@Override
	public Agencia buscar(Long key) {
		Optional<Agencia> agencia = agenciaRepository.findById(key);
		
		return agencia.orElseThrow(() -> new ObjetoNaoEncontradoException(
				"Objeto não encontrado! Id: " + key + ", Tipo: " + Agencia.class.getName()));
	}
	
	@Override
	public Agencia atualizar(Agencia novoAgencia) {
		Agencia agencia = buscar(novoAgencia.getId());
		novoAgencia = atualizaDados(agencia, novoAgencia);
		return agenciaRepository.save(novoAgencia);
	}

	@Override
	protected Agencia atualizaDados(Agencia entity, Agencia newEntity) {
		Agencia agencia = new Agencia(newEntity.getNumero(), newEntity.getNome());
		agencia.setId(entity.getId());
		agencia.setBanco(entity.getBanco());
		
		return agencia;
	}
	
	@Override
	public void remover(Long key) {
		buscar(key);
		
		try {
			agenciaRepository.deleteById(key);
		}
		catch (DataIntegrityViolationException  e) {
			throw new IntegridadeDeDadosException("Não é possível excluir a agencia que possuí contas!");
		}
	}

	public Agencia converteDTOEmEntidade(AgenciaDTO agenciaDto) {
		return new Agencia(agenciaDto.getNumero(), agenciaDto.getNome());
	}

	public Agencia converteDTOEmEntidade(NovaAgenciaDTO agenciaDto) {
		Agencia agencia = new Agencia(agenciaDto.getNumero(), agenciaDto.getNome());
		Banco banco = new Banco(agenciaDto.getBancoId());
		agencia.setBanco(banco);
		
		return agencia;
	}
	
	public Agencia buscarPorNumero(Long bancoId, String numero) {
		Optional<Agencia> agencia = agenciaRepository.buscarPorNumero(bancoId, numero);
		
		return agencia.orElseThrow(() -> new ObjetoNaoEncontradoException(
				"Conta não encontrada! Numero: " + numero));
	}

	public List<Agencia> buscarPorBanco(Long id) {
		return agenciaRepository.buscarPorNumero(id);
	}
}