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
import br.com.gleisonandrade.bancoapi.dto.ContaDTO;
import br.com.gleisonandrade.bancoapi.dto.NovaContaDTO;
import br.com.gleisonandrade.bancoapi.dto.SaqueDTO;
import br.com.gleisonandrade.bancoapi.services.ContaService;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
@RestController
@RequestMapping("/conta")
public class ContaResource {
	@Autowired
	private ContaService contaService;
	
	@GetMapping
	public ResponseEntity<List<ContaDTO>> listar() {
		List<Conta> contas = contaService.listarTodos();
		List<ContaDTO> contasDTO = contas.stream().map(conta -> new ContaDTO(conta)).collect(Collectors.toList());

		return ResponseEntity.ok(contasDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Conta> buscar(@PathVariable Long id) {
		Conta contaBuscado = contaService.buscar(id);

		if (contaBuscado == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(contaBuscado);
	}

	@PostMapping
	public ResponseEntity<Void> adicionar(@Valid @RequestBody NovaContaDTO contaDto) {
		Conta contaCadastrado = contaService.converteDTOEmEntidade(contaDto);
		contaCadastrado = contaService.cadastrar(contaCadastrado);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(contaCadastrado.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping("/sacar")
	public ResponseEntity<Void> sacar(@Valid @RequestBody SaqueDTO saqueDto) {
		Conta conta = contaService.sacar(saqueDto);		
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(conta.getNumero())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
//	@PostMapping
//	public ResponseEntity<Void> depositar(@Valid @RequestBody NovaContaDTO contaDto) {
//		//TODO
//		return ResponseEntity.created(null).build();
//	}
//	
//	@PostMapping
//	public ResponseEntity<Void> transferir(@Valid @RequestBody NovaContaDTO contaDto) {
//		//TODO
//		return ResponseEntity.created(null).build();
//	}
	
//	@GetMapping(path = "/{id}")
//	public ResponseEntity<Conta> extrato(@PathVariable Long id) {
//		Conta contaBuscado = contaService.buscar(id);
//
//		if (contaBuscado == null) {
//			return ResponseEntity.notFound().build();
//		}
//
//		return ResponseEntity.ok(contaBuscado);
//	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Void> atualizar(@PathVariable Long id, @Valid @RequestBody ContaDTO contaDto) {
		Conta contaBuscada = contaService.converteDTOEmEntidade(contaDto);
		contaBuscada.setId(id);
		contaBuscada = contaService.atualizar(contaBuscada);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		contaService.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(path = "/page")
	public ResponseEntity<Page<ContaDTO>> buscaPaginada(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		
		Page<Conta> list = contaService.buscaPaginada(page, linesPerPage, orderBy, direction);
		Page<ContaDTO> listDto = list.map(obj -> new ContaDTO(obj));  
		
		return ResponseEntity.ok().body(listDto);
	}

}
