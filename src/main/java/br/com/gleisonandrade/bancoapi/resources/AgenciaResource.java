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
@RequestMapping("/banco-api/agencia")
public class AgenciaResource {
	
	@Autowired
	private AgenciaService agenciaService;
	
	@GetMapping
	public ResponseEntity<List<Agencia>> listar() {
		List<Agencia> agencias = agenciaService.listarTodos();
		return ResponseEntity.ok(agencias);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Agencia> buscar(@PathVariable Long id) {
		Agencia agenciaBuscado = agenciaService.buscar(id);

		if (agenciaBuscado == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(agenciaBuscado);
	}

	@PostMapping
	public ResponseEntity<Agencia> adicionar(@Valid @RequestBody Agencia agencia) {
		Agencia agenciaCadastrada = agenciaService.salvarOuAtualizar(agencia);
		return ResponseEntity.ok(agenciaCadastrada);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Agencia> atualizar(@PathVariable Long id, @Valid @RequestBody Agencia agencia) {
		Agencia agenciaBuscada = agenciaService.buscar(id);

		if (agenciaBuscada == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(agencia, agenciaBuscada, "id");
		
		return ResponseEntity.ok(agenciaBuscada);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Agencia agenciaBuscada = agenciaService.buscar(id);

		if (agenciaBuscada == null) {
			return ResponseEntity.notFound().build();
		}
		
		agenciaService.remover(agenciaBuscada);
		
		return ResponseEntity.ok().build();
	}

}
