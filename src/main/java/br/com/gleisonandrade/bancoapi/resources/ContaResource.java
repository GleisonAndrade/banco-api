/**
 * 
 */
package br.com.gleisonandrade.bancoapi.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.gleisonandrade.bancoapi.domain.Conta;
import br.com.gleisonandrade.bancoapi.domain.Extrato;
import br.com.gleisonandrade.bancoapi.dto.AtualizaContaDTO;
import br.com.gleisonandrade.bancoapi.dto.ContaDTO;
import br.com.gleisonandrade.bancoapi.dto.DepositoDTO;
import br.com.gleisonandrade.bancoapi.dto.ExtratoDTO;
import br.com.gleisonandrade.bancoapi.dto.NovaContaDTO;
import br.com.gleisonandrade.bancoapi.dto.SaqueDTO;
import br.com.gleisonandrade.bancoapi.dto.TransferenciaDTO;
import br.com.gleisonandrade.bancoapi.services.ContaService;
import br.com.gleisonandrade.bancoapi.services.ExtratoService;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
@RestController
@RequestMapping("/conta")
public class ContaResource {
	@Autowired
	private ContaService contaService;
	
	@Autowired
	private ExtratoService extratoService;

	@GetMapping
	public ResponseEntity<List<ContaDTO>> listar() {
		List<Conta> contas = contaService.listarTodos();
		List<ContaDTO> contasDTO = contas.stream().map(conta -> new ContaDTO(conta)).collect(Collectors.toList());

		return ResponseEntity.ok(contasDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ContaDTO> buscar(@PathVariable Long id) {
		Conta contaBuscada = contaService.buscar(id);
		ContaDTO contaDTO = new ContaDTO(contaBuscada);

		if (contaBuscada == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(contaDTO);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Void> adicionar(@Valid @RequestBody NovaContaDTO contaDto) {
		Conta contaCadastrado = contaService.converteDTOEmEntidade(contaDto);
		contaCadastrado = contaService.cadastrar(contaCadastrado);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(contaCadastrado.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@PathVariable Long id, @Valid @RequestBody AtualizaContaDTO contaDto) {
		Conta contaBuscada = contaService.converteDTOEmEntidade(contaDto);
		contaBuscada.setId(id);
		contaBuscada = contaService.atualizar(contaBuscada);
		
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		contaService.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/sacar")
	public ResponseEntity<Void> sacar(@Valid @RequestBody SaqueDTO saqueDto) {
		Conta conta = contaService.sacar(saqueDto);

		URI uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/conta/{id}").build()
				.expand(conta.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PostMapping("/depositar")
	public ResponseEntity<Void> depositar(@Valid @RequestBody DepositoDTO depositoDTO) {
		Conta conta = contaService.depositar(depositoDTO);

		URI uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/conta/{id}").build()
				.expand(conta.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PostMapping("/transferir")
	public ResponseEntity<Void> transferir(@Valid @RequestBody TransferenciaDTO transferenciaDTO) {
		Conta conta = contaService.transferir(transferenciaDTO);

		URI uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/conta/{id}").build()
				.expand(conta.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{id}/extrato/")
	public ResponseEntity<List<ExtratoDTO>> extratos(@PathVariable Long id){
		List<Extrato> extratos = extratoService.listarTodosPorContaId(id);
		List<ExtratoDTO> extratosDTO = extratos.stream().map(extrato -> new ExtratoDTO(extrato)).collect(Collectors.toList());

		return ResponseEntity.ok(extratosDTO);
	}
	
	@GetMapping("/{id}/extrato/{extratoId}")
	public ResponseEntity<ExtratoDTO> extrato(@PathVariable Long id, @PathVariable Long extratoId){
		Extrato extrato = extratoService.buscar(id, extratoId);
		ExtratoDTO extratoDTO = new ExtratoDTO(extrato);

		return ResponseEntity.ok(extratoDTO);
	}	

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/page")
	public ResponseEntity<Page<ContaDTO>> buscaPaginada(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Conta> list = contaService.buscaPaginada(page, linesPerPage, orderBy, direction);
		Page<ContaDTO> listDto = list.map(obj -> new ContaDTO(obj));

		return ResponseEntity.ok().body(listDto);
	}

}
