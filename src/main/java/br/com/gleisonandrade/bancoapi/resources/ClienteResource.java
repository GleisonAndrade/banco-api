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

import br.com.gleisonandrade.bancoapi.domain.Cliente;
import br.com.gleisonandrade.bancoapi.services.ClienteService;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */

@RestController
@RequestMapping("/banco-api/cliente")
public class ClienteResource {
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> listar() {
		List<Cliente> clientes = clienteService.listarTodos();
		return ResponseEntity.ok(clientes);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long id) {
		Cliente clienteBuscado = clienteService.buscar(id);

		if (clienteBuscado == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(clienteBuscado);
	}

	@PostMapping
	public ResponseEntity<Cliente> adicionar(@Valid @RequestBody Cliente cliente) {
		Cliente clienteCadastrada = clienteService.salvar(cliente);
		return ResponseEntity.ok(clienteCadastrada);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
		Cliente clienteBuscada = clienteService.buscar(id);

		if (clienteBuscada == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(cliente, clienteBuscada, "id");
		
		return ResponseEntity.ok(clienteBuscada);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Cliente clienteBuscada = clienteService.buscar(id);

		if (clienteBuscada == null) {
			return ResponseEntity.notFound().build();
		}
		
		clienteService.remover(id);
		
		return ResponseEntity.ok().build();
	}

}