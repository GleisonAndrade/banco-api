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

import br.com.gleisonandrade.bancoapi.domain.Extrato;
import br.com.gleisonandrade.bancoapi.services.ExtratoService;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */

@RestController
@RequestMapping("/banco-api/extrato")
public class ExtratoResource {
	@Autowired
	private ExtratoService extratoService;

	@GetMapping
	public ResponseEntity<List<Extrato>> listar() {
		List<Extrato> extratos = extratoService.listarTodos();
		return ResponseEntity.ok(extratos);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Extrato> buscar(@PathVariable Long id) {
		Extrato extratoBuscado = extratoService.buscar(id);

		if (extratoBuscado == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(extratoBuscado);
	}

	@PostMapping
	public ResponseEntity<Extrato> adicionar(@Valid @RequestBody Extrato extrato) {
		Extrato extratoCadastrada = extratoService.salvar(extrato);
		return ResponseEntity.ok(extratoCadastrada);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Extrato> atualizar(@PathVariable Long id, @Valid @RequestBody Extrato extrato) {
		Extrato extratoBuscada = extratoService.buscar(id);

		if (extratoBuscada == null) {
			return ResponseEntity.notFound().build();
		}

		BeanUtils.copyProperties(extrato, extratoBuscada, "id");

		return ResponseEntity.ok(extratoBuscada);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Extrato extratoBuscada = extratoService.buscar(id);

		if (extratoBuscada == null) {
			return ResponseEntity.notFound().build();
		}

		extratoService.remover(id);

		return ResponseEntity.ok().build();
	}
}