/**
 * 
 */
package br.com.gleisonandrade.bancoapi.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gleisonandrade.bancoapi.domain.Conta;
import br.com.gleisonandrade.bancoapi.services.ContaService;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
@RestController
@RequestMapping("/banco-api/conta")
public class ContaResource {
	@Autowired
	private ContaService contaService;
	
	@GetMapping
	public ResponseEntity<List<Conta>> listar() {
		List<Conta> contas = contaService.listarTodos();
		return ResponseEntity.ok(contas);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Conta> buscar(@PathVariable Long id) {
		Conta contaBuscado = contaService.buscar(id);

		if (contaBuscado == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(contaBuscado);
	}

	@PostMapping
	public ResponseEntity<Conta> adicionar(@Valid @RequestBody Conta conta) {
		Conta contaCadastrada = contaService.salvar(conta);
		return ResponseEntity.ok(contaCadastrada);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Conta> atualizar(@PathVariable Long id, @Valid @RequestBody Conta conta) {
		Conta contaBuscada = contaService.buscar(id);

		if (contaBuscada == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(conta, contaBuscada, "id");
		
		return ResponseEntity.ok(contaBuscada);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Conta contaBuscada = contaService.buscar(id);

		if (contaBuscada == null) {
			return ResponseEntity.notFound().build();
		}
		
		contaService.remover(id);
		
		return ResponseEntity.ok().build();
	}
}
