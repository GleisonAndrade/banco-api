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

import br.com.gleisonandrade.bancoapi.domain.Agencia;
import br.com.gleisonandrade.bancoapi.services.AgenciaService;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */

@RestController
@RequestMapping("/cliente-api/cliente")
public class ClienteResource {
	@Autowired
	private AgenciaService clienteService;
	
	@GetMapping
	public ResponseEntity<List<Agencia>> listar() {
		List<Agencia> clientes = clienteService.listarTodos();
		return ResponseEntity.ok(clientes);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Agencia> buscar(@PathVariable Long id) {
		Agencia clienteBuscado = clienteService.buscar(id);

		if (clienteBuscado == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(clienteBuscado);
	}

	@PostMapping
	public ResponseEntity<Agencia> adicionar(@Valid @RequestBody Agencia cliente) {
		Agencia clienteCadastrada = clienteService.salvarOuAtualizar(cliente);
		return ResponseEntity.ok(clienteCadastrada);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Agencia> atualizar(@PathVariable Long id, @Valid @RequestBody Agencia cliente) {
		Agencia clienteBuscada = clienteService.buscar(id);

		if (clienteBuscada == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(cliente, clienteBuscada, "id");
		
		return ResponseEntity.ok(clienteBuscada);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Agencia clienteBuscada = clienteService.buscar(id);

		if (clienteBuscada == null) {
			return ResponseEntity.notFound().build();
		}
		
		clienteService.remover(clienteBuscada);
		
		return ResponseEntity.ok().build();
	}

}