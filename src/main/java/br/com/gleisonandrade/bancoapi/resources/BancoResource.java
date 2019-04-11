/**
 * 
 */
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
import br.com.gleisonandrade.bancoapi.domain.Banco;
import br.com.gleisonandrade.bancoapi.dto.AgenciaDTO;
import br.com.gleisonandrade.bancoapi.dto.BancoDTO;
import br.com.gleisonandrade.bancoapi.services.AgenciaService;
import br.com.gleisonandrade.bancoapi.services.BancoService;
import io.swagger.annotations.ApiOperation;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */

@RestController
@RequestMapping("/banco")
public class BancoResource {

	@Autowired
	private BancoService bancoService;
	
	@Autowired
	private AgenciaService agenciaService;

	@ApiOperation(value = "Mostra a lista de bancos cadastrados no sistema.", response = List.class)
	@GetMapping
	public ResponseEntity<List<BancoDTO>> listar() {
		List<Banco> bancos = bancoService.listarTodos();
		List<BancoDTO> bancosDTO = bancos.stream().map(banco -> new BancoDTO(banco)).collect(Collectors.toList());

		return ResponseEntity.ok(bancosDTO);
	}

	@ApiOperation(value = "Mostra os dados de um banco com o id passado cadastrada no sistema.", response = AgenciaDTO.class)
	@GetMapping("/{id}")
	public ResponseEntity<Banco> buscar(@PathVariable Long id) {
		Banco bancoBuscado = bancoService.buscar(id);

		if (bancoBuscado == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(bancoBuscado);
	}

	@ApiOperation(value = "Cadastra um novo banco no sistema.")
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Void> adicionar(@Valid @RequestBody BancoDTO bancoDto) {
		Banco bancoCadastrado = bancoService.converteDTOEmEntidade(bancoDto);
		bancoCadastrado = bancoService.salvar(bancoCadastrado);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(bancoCadastrado.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Atualiza um banco no sistema.")
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@PathVariable Long id, @Valid @RequestBody BancoDTO bancoDto) {
		Banco bancoBuscada = bancoService.converteDTOEmEntidade(bancoDto);
		bancoBuscada.setId(id);
		bancoBuscada = bancoService.atualizar(bancoBuscada);

		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value = "Remove um banco do sistema.")
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		bancoService.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Mostra a lista paginada de bancos cadastrados no sistema.", response = Page.class)
	@GetMapping("/page")
	public ResponseEntity<Page<BancoDTO>> buscaPaginada(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		
		Page<Banco> list = bancoService.buscaPaginada(page, linesPerPage, orderBy, direction);
		Page<BancoDTO> listDto = list.map(obj -> new BancoDTO(obj));  
		
		return ResponseEntity.ok().body(listDto);
	}
	
	@ApiOperation(value = "Mostra a lista de agências de um banco especifico.", response = List.class)
	@GetMapping("/{id}/agencia")
	public ResponseEntity<List<AgenciaDTO>> buscarAgencias(@PathVariable Long id) {
		List<Agencia> agencias = agenciaService.buscarPorBanco(id);
		List<AgenciaDTO> agenciasDTO = agencias.stream().map(agencia -> new AgenciaDTO(agencia)).collect(Collectors.toList());

		return ResponseEntity.ok(agenciasDTO);
	}
	
	@ApiOperation(value = "Mostra os dados de uma agência pelo número que pertence ao banco com o id passado cadastrada no sistema.", response = AgenciaDTO.class)
	@GetMapping("/{id}/agencia/{numero}")
	public ResponseEntity<AgenciaDTO> buscarAgencia(@PathVariable Long id, @PathVariable String numero) {
		Agencia agenciaBuscada = agenciaService.buscarPorNumero(id, numero);

		if (agenciaBuscada == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(new AgenciaDTO(agenciaBuscada));
	}

}