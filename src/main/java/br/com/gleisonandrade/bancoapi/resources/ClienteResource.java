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

import br.com.gleisonandrade.bancoapi.domain.Cliente;
import br.com.gleisonandrade.bancoapi.dto.ClienteDTO;
import br.com.gleisonandrade.bancoapi.dto.NovoClienteDTO;
import br.com.gleisonandrade.bancoapi.services.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */

@RestController
@RequestMapping("/cliente")
public class ClienteResource {
	@Autowired
	private ClienteService clienteService;
	
	@ApiOperation(value = "Mostra a lista de clientes cadastrados no sistema.", response = List.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Lista de clientes recuperada com sucesso")
	})
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> listar() {
		List<Cliente> clientes = clienteService.listarTodos();
		List<ClienteDTO> clientesDTO = clientes.stream().map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList());

		return ResponseEntity.ok(clientesDTO);
	}
	
	@ApiOperation(value = "Mostra os dados do cliente com o id passado cadastrado no sistema.", response = ClienteDTO.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 404, message = "Não existe cliente cadastrado no sistema com esse id")
	})
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> buscar(@PathVariable Long id) {
		Cliente clienteBuscado = clienteService.buscar(id);

		if (clienteBuscado == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(new ClienteDTO(clienteBuscado));
	}

	@ApiOperation(value = "Cadastra um novo cliente no sistema.")
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Void> adicionar(@Valid @RequestBody NovoClienteDTO novoClienteDto) {
		Cliente clienteCadastrado = clienteService.converteNovoClienteDTOEmEntidade(novoClienteDto);
		clienteCadastrado = clienteService.salvar(clienteCadastrado);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteCadastrado.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Atualiza um cliente no sistema.")
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@PathVariable Long id, @Valid @RequestBody NovoClienteDTO clienteDto) {
		Cliente clienteBuscada = clienteService.converteNovoClienteDTOEmEntidade(clienteDto);
		clienteBuscada.setId(id);
		clienteBuscada = clienteService.atualizar(clienteBuscada);

		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value = "Remove um cliente no sistema.")
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		clienteService.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Mostra a lista paginada de clientes cadastrados no sistema.", response = Page.class)
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/page")
	public ResponseEntity<Page<ClienteDTO>> buscaPaginada(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		
		Page<Cliente> list = clienteService.buscaPaginada(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));  
		
		return ResponseEntity.ok().body(listDto);
	}
	
}