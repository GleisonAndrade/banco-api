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
@RequestMapping("/extrato-api/extrato")
public class ExtratoResource {
	@Autowired
	private AgenciaService extratoService;

	@GetMapping
	public ResponseEntity<List<Agencia>> listar() {
		List<Agencia> extratos = extratoService.listarTodos();
		return ResponseEntity.ok(extratos);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Agencia> buscar(@PathVariable Long id) {
		Agencia extratoBuscado = extratoService.buscar(id);

		if (extratoBuscado == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(extratoBuscado);
	}

	@PostMapping
	public ResponseEntity<Agencia> adicionar(@Valid @RequestBody Agencia extrato) {
		Agencia extratoCadastrada = extratoService.salvarOuAtualizar(extrato);
		return ResponseEntity.ok(extratoCadastrada);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Agencia> atualizar(@PathVariable Long id, @Valid @RequestBody Agencia extrato) {
		Agencia extratoBuscada = extratoService.buscar(id);

		if (extratoBuscada == null) {
			return ResponseEntity.notFound().build();
		}

		BeanUtils.copyProperties(extrato, extratoBuscada, "id");

		return ResponseEntity.ok(extratoBuscada);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Agencia extratoBuscada = extratoService.buscar(id);

		if (extratoBuscada == null) {
			return ResponseEntity.notFound().build();
		}

		extratoService.remover(extratoBuscada);

		return ResponseEntity.ok().build();
	}
}