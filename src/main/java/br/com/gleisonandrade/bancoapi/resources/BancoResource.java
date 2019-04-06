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
@RequestMapping("/banco-api/banco")
public class BancoResource {

	@Autowired
	private AgenciaService bancoService;
	
	@GetMapping
	public ResponseEntity<List<Agencia>> listar() {
		List<Agencia> bancos = bancoService.listarTodos();
		return ResponseEntity.ok(bancos);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Agencia> buscar(@PathVariable Long id) {
		Agencia bancoBuscado = bancoService.buscar(id);

		if (bancoBuscado == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(bancoBuscado);
	}

	@PostMapping
	public ResponseEntity<Agencia> adicionar(@Valid @RequestBody Agencia banco) {
		Agencia bancoCadastrada = bancoService.salvarOuAtualizar(banco);
		return ResponseEntity.ok(bancoCadastrada);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Agencia> atualizar(@PathVariable Long id, @Valid @RequestBody Agencia banco) {
		Agencia bancoBuscada = bancoService.buscar(id);

		if (bancoBuscada == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(banco, bancoBuscada, "id");
		
		return ResponseEntity.ok(bancoBuscada);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Agencia bancoBuscada = bancoService.buscar(id);

		if (bancoBuscada == null) {
			return ResponseEntity.notFound().build();
		}
		
		bancoService.remover(bancoBuscada);
		
		return ResponseEntity.ok().build();
	}

}