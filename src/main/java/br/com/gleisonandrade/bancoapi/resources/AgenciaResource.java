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

import br.com.gleisonandrade.bancoapi.domain.Agencia;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
@RestController
@RequestMapping("/agencia-api/agencia")
public class AgenciaResource {
	@GetMapping
	public ResponseEntity<List<Agencia>> listar() {
		List<Agencia> agencias = new ArrayList<>();
		return ResponseEntity.ok(agencias);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Agencia> buscar(@PathVariable Long id) {
		Agencia agenciaBuscado = null;

		if (agenciaBuscado == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(agenciaBuscado);
	}

	@PostMapping
	public ResponseEntity<Agencia> adicionar(@Valid @RequestBody Agencia agencia) {
		return ResponseEntity.ok(new Agencia());
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Agencia> atualizar(@PathVariable Long id, @Valid @RequestBody Agencia agencia) {
		Agencia agenciaBuscado = null;

		if (agenciaBuscado == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(agencia, agenciaBuscado, "id");
		
		return ResponseEntity.ok(agenciaBuscado);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		return ResponseEntity.noContent().build();
	}

}
