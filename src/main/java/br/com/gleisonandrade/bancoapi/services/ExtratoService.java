/**
 * 
 */
package br.com.gleisonandrade.bancoapi.services;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gleisonandrade.bancoapi.domain.Conta;
import br.com.gleisonandrade.bancoapi.domain.Extrato;
import br.com.gleisonandrade.bancoapi.domain.enuns.TipoOperacao;
import br.com.gleisonandrade.bancoapi.repositories.ExtratoRepository;
import br.com.gleisonandrade.bancoapi.services.exceptions.NegocioException;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
@Service
public class ExtratoService extends GenericServiceImpl<Extrato, Long>{
	@Autowired
	private ExtratoRepository extratoRepository;
	
	public ExtratoService(ExtratoRepository extratoRepository) {
		super(extratoRepository);
		this.extratoRepository = extratoRepository;
	}

	@Override
	protected Extrato atualizaDados(Extrato entity, Extrato newEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	public Extrato gerar(Conta conta, TipoOperacao tipo, Double valor) {
		if(TipoOperacao.SAQUE.equals(tipo)) {
			Extrato extrato = new Extrato(Calendar.getInstance(), tipo, valor, conta);
			extrato = extratoRepository.save(extrato);
			return extrato;
		}
		
		throw new NegocioException("Não foi possível gerar um extrato!");
	}
}
