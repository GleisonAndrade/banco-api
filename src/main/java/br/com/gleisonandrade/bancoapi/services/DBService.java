/**
 * 
 */
package br.com.gleisonandrade.bancoapi.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.gleisonandrade.bancoapi.domain.Agencia;
import br.com.gleisonandrade.bancoapi.domain.Banco;
import br.com.gleisonandrade.bancoapi.domain.Cliente;
import br.com.gleisonandrade.bancoapi.domain.Conta;
import br.com.gleisonandrade.bancoapi.domain.enuns.TipoDeConta;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */

@Service
public class DBService {
	@Autowired
	private ContaService contaService;
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

	public void instantiateTestDatabase() {
		Banco b1 = new Banco("Banco do Brasil");
		Banco b2 = new Banco("Caixa Econômica Federal");
		Banco b3 = new Banco("Bradesco");
		Banco b4 = new Banco("NuConta");

		bancoService.saveAll(Arrays.asList(b1, b2, b3, b4));

		Agencia b1_ag1 = new Agencia("0000-0", "Banco do Brasil Centro", b1);
		Agencia b1_ag2 = new Agencia("0000-0", "Banco do Brasil Sul", b1);

		Agencia b2_ag1 = new Agencia("0000-0", "Caixa Av. Miguel Rosa", b2);
		Agencia b2_ag2 = new Agencia("0000-0", "Caixa Av. Frei Serafim", b2);

		agenciaService.saveAll(Arrays.asList(b1_ag1, b1_ag2, b2_ag1, b2_ag2));

		Cliente cliente = new Cliente("Gleison Andrade", "756.401.413-01", bCryptPasswordEncoder.encode("123456"));
		Cliente cliente2 = new Cliente("Francisco Andrade", "672.281.173-52", bCryptPasswordEncoder.encode("123456"));

		clienteService.saveAll(Arrays.asList(cliente, cliente2));

		Conta conta = new Conta("00001-0", TipoDeConta.CORRENTE, cliente, b1_ag1, 0.0);
		Conta conta2 = new Conta("00002-0", TipoDeConta.POUPANCA, cliente2, b2_ag1, 0.0);

		contaService.saveAll(Arrays.asList(conta, conta2));
	}

}
