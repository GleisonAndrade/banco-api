package br.com.gleisonandrade.bancoapi.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.gleisonandrade.bancoapi.domain.Agencia;
import br.com.gleisonandrade.bancoapi.dto.AgenciaDTO;
import br.com.gleisonandrade.bancoapi.dto.NovaAgenciaDTO;
import br.com.gleisonandrade.bancoapi.services.AgenciaService;
import io.swagger.annotations.ApiOperation;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
@RestController
@RequestMapping("/agencia")
public class AgenciaResource {
	
	@Autowired
	private AgenciaService agenciaService;
	
	@ApiOperation(value = "Mostra a lista de agencias cadastradas no sistema.", response = List.class)
	@GetMapping
	public ResponseEntity<List<AgenciaDTO>> listar() {
		List<Agencia> agencias = agenciaService.listarTodos();
		List<AgenciaDTO> agenciasDTO = agencias.stream().map(agencia -> new AgenciaDTO(agencia)).collect(Collectors.toList());

		return ResponseEntity.ok(agenciasDTO);
	}
	
	@ApiOperation(value = "Mostra os dados de uma agência com o id passado cadastrada no sistema.", response = AgenciaDTO.class)
	@GetMapping("/{id}")
	public ResponseEntity<AgenciaDTO> buscar(@PathVariable Long id) {
		Agencia agenciaBuscada = agenciaService.buscar(id);

		if (agenciaBuscada == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(new AgenciaDTO(agenciaBuscada));
	}

	@ApiOperation(value = "Cadastra uma nova agência no sistema.")
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Void> adicionar(@Valid @RequestBody NovaAgenciaDTO agenciaDto) {
		Agencia agenciaCadastrado = agenciaService.converteDTOEmEntidade(agenciaDto);
		agenciaCadastrado = agenciaService.salvar(agenciaCadastrado);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(agenciaCadastrado.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Atualiza uma agência no sistema.")
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@PathVariable Long id, @Valid @RequestBody AgenciaDTO agenciaDto) {
		Agencia agenciaBuscada = agenciaService.converteDTOEmEntidade(agenciaDto);
		agenciaBuscada.setId(id);
		agenciaBuscada = agenciaService.atualizar(agenciaBuscada);

		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value = "Remove uma agência no sistema.")
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		agenciaService.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Mostra a lista paginada de agencias cadastradas no sistema.", response = Page.class)
	@GetMapping("/page")
	public ResponseEntity<Page<AgenciaDTO>> buscaPaginada(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		
		Page<Agencia> list = agenciaService.buscaPaginada(page, linesPerPage, orderBy, direction);
		Page<AgenciaDTO> listDto = list.map(obj -> new AgenciaDTO(obj));  
		
		return ResponseEntity.ok().body(listDto);
	}

}
