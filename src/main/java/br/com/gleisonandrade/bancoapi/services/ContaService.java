/**
 * 
 */
package br.com.gleisonandrade.bancoapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.gleisonandrade.bancoapi.domain.Agencia;
import br.com.gleisonandrade.bancoapi.domain.Cliente;
import br.com.gleisonandrade.bancoapi.domain.Conta;
import br.com.gleisonandrade.bancoapi.domain.enuns.TipoDeConta;
import br.com.gleisonandrade.bancoapi.domain.enuns.TipoOperacao;
import br.com.gleisonandrade.bancoapi.dto.ContaDTO;
import br.com.gleisonandrade.bancoapi.dto.DepositoDTO;
import br.com.gleisonandrade.bancoapi.dto.NovaContaDTO;
import br.com.gleisonandrade.bancoapi.dto.SaqueDTO;
import br.com.gleisonandrade.bancoapi.dto.TransferenciaDTO;
import br.com.gleisonandrade.bancoapi.repositories.ContaRepository;
import br.com.gleisonandrade.bancoapi.services.exceptions.IntegridadeDeDadosException;
import br.com.gleisonandrade.bancoapi.services.exceptions.NegocioException;
import br.com.gleisonandrade.bancoapi.services.exceptions.ObjetoNaoEncontradoException;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
@Service
public class ContaService extends GenericServiceImpl<Conta, Long> {
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private AgenciaService agenciaService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private BancoService bancoService;
	
	@Autowired
	private ExtratoService extratoService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserService userService;


	public ContaService(ContaRepository contaRepository) {
		super(contaRepository);
		this.contaRepository = contaRepository;
	}

	@Override
	public Conta buscar(Long key) {
		Optional<Conta> conta = contaRepository.findById(key);
		
		userService.validaClienteConta(key);

		return conta.orElseThrow(() -> new ObjetoNaoEncontradoException(
				"Objeto não encontrado! Id: " + key + ", Tipo: " + Conta.class.getName()));
	}

	@Override
	public Conta atualizar(Conta novoConta) {
		Conta conta = buscar(novoConta.getId());
		novoConta = atualizaDados(conta, novoConta);
		return contaRepository.save(novoConta);
	}

	@Override
	protected Conta atualizaDados(Conta entity, Conta newEntity) {
		Conta conta = new Conta(newEntity.getNumero(), newEntity.getTipo(), newEntity.getSaldo());
		conta.setId(entity.getId());
		conta.setCliente(entity.getCliente());
		conta.setAgencia(entity.getAgencia());

		return conta;
	}

	@Override
	public void remover(Long key) {
		buscar(key);

		try {
			contaRepository.deleteById(key);
		} catch (DataIntegrityViolationException e) {
			throw new IntegridadeDeDadosException("Não é possível excluir a conta que possuí contas!");
		}
	}

	public Conta converteDTOEmEntidade(ContaDTO contaDto) {
		return new Conta(contaDto.getId(), contaDto.getNumero(), TipoDeConta.valueOf(contaDto.getTipo()),
				contaDto.getSaldo());
	}

	public Conta converteDTOEmEntidade(NovaContaDTO contaDto) {
		Conta conta = new Conta(contaDto.getNumero(), TipoDeConta.valueOf(contaDto.getTipo()),
				contaDto.getSaldo());

		Cliente cliente = new Cliente(contaDto.getNome(), contaDto.getCpf(), bCryptPasswordEncoder.encode(contaDto.getSenha()));
		Agencia agencia = agenciaService.buscarPorNumero(contaDto.getBancoId(), contaDto.getAgenciaNumero());

		conta.setCliente(cliente);
		conta.setAgencia(agencia);

		return conta;
	}

	public Conta buscarPorNumero(String numero) {
		Optional<Conta> conta = contaRepository.buscarPorNumero(numero);

		return conta.orElseThrow(() -> new ObjetoNaoEncontradoException("Conta não encontrada! número: " + numero));
	}

	public Conta buscarPorNumeroAgenciaNumeroBancoId(String contaNumero, String agenciaNumero, Long bancoId) {
		Optional<Conta> conta = contaRepository.buscarPorNumeroAgenciaNumeroBancoId(contaNumero, agenciaNumero,
				bancoId);

		return conta.orElseThrow(() -> new ObjetoNaoEncontradoException(
				"Conta não encontrada! número: " + contaNumero + ", na agência de número: " + agenciaNumero));
	}

	public Conta cadastrar(Conta conta) {

		try {
			verificaConta(conta.getNumero(), conta.getAgencia().getNumero(), conta.getAgencia().getBanco().getId());
		} catch (ObjetoNaoEncontradoException e) {
			// Se cliente não existir persiste um novo, caso exista buscar para adicionar

			Cliente cliente = clienteService.salvar(conta.getCliente());
			Conta novaConta = new Conta(conta.getNumero(), conta.getTipo(), cliente, conta.getAgencia(),
					conta.getSaldo());

			return salvar(novaConta);
		}

		throw new NegocioException("Já existe uma conta cadastrada na agência com o número informado!");
	}

	public Conta verificaConta(String contaNumero, String agenciaNumero, Long bancoId) {
		// Verifiar se banco existe
		bancoService.buscar(bancoId);

		// Verificar se existe a agência
		agenciaService.buscarPorNumero(bancoId, agenciaNumero);

		// Verificar se existe a conta com os dados informados
		Conta conta = buscarPorNumeroAgenciaNumeroBancoId(contaNumero, agenciaNumero, bancoId);

		return conta;
	}

	public Conta sacar(SaqueDTO saqueDto) {
		Conta conta = verificaConta(saqueDto.getContaNumero(), saqueDto.getAgenciaNumero(), saqueDto.getBancoId());
		
		userService.validaClienteConta(conta);
		
		return debitar(conta, saqueDto.getValor());
	}

	

	public Conta depositar(DepositoDTO depositoDTO) {
		Conta conta = verificaConta(depositoDTO.getContaNumero(), depositoDTO.getAgenciaNumero(), depositoDTO.getBancoId());

		return creditar(conta, depositoDTO.getValor());
	}

	public Conta transferir(TransferenciaDTO transferenciaDTO) {
		Conta contaOrigem = verificaConta(transferenciaDTO.getContaOrigemNumero(), transferenciaDTO.getAgenciaOrigemNumero(), transferenciaDTO.getBancoOrigemId());
		
		userService.validaClienteConta(contaOrigem);
		
		Conta contaDestino = verificaConta(transferenciaDTO.getContaDestinoNumero(),
				transferenciaDTO.getAgenciaDestinoNumero(), transferenciaDTO.getBancoDestinoId());

		return transferir(contaOrigem, contaDestino, transferenciaDTO.getValor());		
	}
	
	private Conta debitar(Conta conta, Double valor) {
		return debitar(conta, valor, null, null);
	}

	private Conta debitar(Conta conta, Double valor, TipoOperacao tipo, Conta contaDestino) {
		if (valor <= conta.getSaldo()) {
			conta.setSaldo(conta.getSaldo() - valor);
			conta = contaRepository.save(conta);

			extratoService.gerar(false, conta, tipo == null ? TipoOperacao.SAQUE : tipo, valor, contaDestino);

			return conta;
		}

		throw new NegocioException(
				String.format("A conta não possuí saldo suficiente para essa operação! Saldo: {0}, valor a ser debitado {1}",
						conta.getSaldo(), valor));
	}
	
	private Conta creditar(Conta conta, Double valor) {
		return creditar(conta, valor, null, null);
	}

	private Conta creditar(Conta conta, Double valor, TipoOperacao tipo, Conta contaOrigem) {
		conta.setSaldo(conta.getSaldo() + valor);
		conta = contaRepository.save(conta);

		extratoService.gerar(true, conta, tipo == null ? TipoOperacao.DEPOSITO : tipo, valor, contaOrigem);

		return conta;
	}
	
	private Conta transferir(Conta contaOrigem, Conta contaDestino, Double valor) {
		contaOrigem = debitar(contaOrigem, valor, TipoOperacao.TRANSFERENCIA, contaDestino);
		contaDestino = creditar(contaDestino, valor, TipoOperacao.TRANSFERENCIA, contaOrigem);
		
		return contaOrigem;
	}
}