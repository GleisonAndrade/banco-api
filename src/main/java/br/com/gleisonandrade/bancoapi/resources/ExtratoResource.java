/**
 * 
 */
package br.com.gleisonandrade.bancoapi.resources;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
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

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */

@RestController
@RequestMapping("/extrato-api/extrato")
public class ExtratoResource {
	@GetMapping
	public ResponseEntity<List<Extrato>> listar() {
		List<Extrato> extratos = new ArrayList<>();
		return ResponseEntity.ok(extratos);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Extrato> buscar(@PathVariable Long id) {
		Extrato extratoBuscado = null;

		if (extratoBuscado == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(extratoBuscado);
	}

	@PostMapping
	public ResponseEntity<Extrato> adicionar(@Valid @RequestBody Extrato extrato) {
		return ResponseEntity.ok(new Extrato());
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Extrato> atualizar(@PathVariable Long id, @Valid @RequestBody Extrato extrato) {
		Extrato extratoBuscado = null;

		if (extratoBuscado == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(extrato, extratoBuscado, "id");
		
		return ResponseEntity.ok(extratoBuscado);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		return ResponseEntity.noContent().build();
	}

	
}