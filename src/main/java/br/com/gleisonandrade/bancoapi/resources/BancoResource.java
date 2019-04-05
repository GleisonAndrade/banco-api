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

import br.com.gleisonandrade.bancoapi.domain.Banco;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */

@RestController
@RequestMapping("/banco-api/banco")
public class BancoResource {

	@GetMapping
	public ResponseEntity<List<Banco>> listar() {
		List<Banco> bancos = new ArrayList<>();
		return ResponseEntity.ok(bancos);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Banco> buscar(@PathVariable Long id) {
		Banco bancoBuscado = null;

		if (bancoBuscado == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(bancoBuscado);
	}

	@PostMapping
	public ResponseEntity<Banco> adicionar(@Valid @RequestBody Banco banco) {
		return ResponseEntity.ok(new Banco());
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Banco> atualizar(@PathVariable Long id, @Valid @RequestBody Banco banco) {
		Banco bancoBuscado = null;

		if (bancoBuscado == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(banco, bancoBuscado, "id");
		
		return ResponseEntity.ok(bancoBuscado);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		return ResponseEntity.noContent().build();
	}

}