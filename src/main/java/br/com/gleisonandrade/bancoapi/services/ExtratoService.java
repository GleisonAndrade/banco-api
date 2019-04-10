/**
 * 
 */
package br.com.gleisonandrade.bancoapi.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gleisonandrade.bancoapi.domain.Conta;
import br.com.gleisonandrade.bancoapi.domain.Extrato;
import br.com.gleisonandrade.bancoapi.domain.enuns.TipoOperacao;
import br.com.gleisonandrade.bancoapi.repositories.ExtratoRepository;
import br.com.gleisonandrade.bancoapi.util.DataUtil;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
@Service
public class ExtratoService extends GenericServiceImpl<Extrato, Long>{
	@Autowired
	private ExtratoRepository extratoRepository;
	
	@Autowired
	private DataUtil dataUtil;

	@Autowired
	private UserService userService;
	
	public ExtratoService(ExtratoRepository extratoRepository) {
		super(extratoRepository);
		this.extratoRepository = extratoRepository;
	}

	@Override
	protected Extrato atualizaDados(Extrato entity, Extrato newEntity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Extrato gerar(Boolean credito, Conta conta, TipoOperacao tipo, Double valor) {
		return gerar(credito, conta, tipo, valor, null);
	}

	public Extrato gerar(Boolean credito, Conta conta, TipoOperacao tipo, Double valor, Conta contaDestino) {
		Calendar data = Calendar.getInstance();
		
		Extrato extrato = new Extrato(data, tipo, valor, conta);
		extrato.setInformacoes(controiInformacoes(credito, conta, tipo, valor, contaDestino, data));
		extrato = extratoRepository.save(extrato);
		
		return extrato;
	}

	private String controiInformacoes(Boolean credito, Conta conta, TipoOperacao tipo, Double valor, Conta contaDestino, Calendar data) {
		if(credito) {
			return informacaoCredito(contaDestino, tipo, valor, conta, data);
		}else{
			return informacaoDebito(conta, tipo, valor, contaDestino, data);
		}
	}

	private String informacaoDebito(Conta conta, TipoOperacao tipo, Double valor, Conta contaDestino, Calendar data) {
		if(TipoOperacao.TRANSFERENCIA.equals(tipo)) {
			return String.format("DATA: %s\n"
					+ "TRANFERÊNCIA REALIZADA DE R$ %.2f\n"
					+ "PARA %s, CONTA: %s AG: %s", dataUtil.dataFormatada(data.getTime()), valor, conta.getCliente().getNome().split(" ")[0],
					conta.getNumero(), conta.getAgencia().getNumero());
		}else {
			return String.format("DATA: %s\n"
					+ "SAQUE DE R$ %.2f", dataUtil.dataFormatada(data.getTime()), valor);
		}
	}

	private String informacaoCredito(Conta conta, TipoOperacao tipo, Double valor, Conta contaDestino, Calendar data) {
		if(TipoOperacao.TRANSFERENCIA.equals(tipo)) {
			return String.format("DATA: %s\n"
					+ "TRANFERÊNCIA RECEBIDA DE R$ %.2f\n"
					+ "POR %s, CONTA: %s AG: %s", dataUtil.dataFormatada(data.getTime()), valor, contaDestino.getCliente().getNome().split(" ")[0],
					contaDestino.getNumero(), contaDestino.getAgencia().getNumero());
		}else {
			return String.format("DATA: %s\n"
					+ "DEPÓSITO DE R$ %.2f", dataUtil.dataFormatada(data.getTime()), valor);
		}
	}

	public List<Extrato> listarTodosPorContaId(Long id) {
		userService.validaClienteConta(id);
		return extratoRepository.listarTodosPorContaId(id);
	}

	public Extrato buscar(Long id, Long extratoId) {
		userService.validaClienteConta(id);
		return buscar(extratoId);
	}
}