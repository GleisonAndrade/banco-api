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

import br.com.gleisonandrade.bancoapi.domain.Conta;
import br.com.gleisonandrade.bancoapi.domain.Extrato;
import br.com.gleisonandrade.bancoapi.dto.AtualizaContaDTO;
import br.com.gleisonandrade.bancoapi.dto.ContaDTO;
import br.com.gleisonandrade.bancoapi.dto.DepositoDTO;
import br.com.gleisonandrade.bancoapi.dto.ExtratoDTO;
import br.com.gleisonandrade.bancoapi.dto.NovaContaDTO;
import br.com.gleisonandrade.bancoapi.dto.SaqueDTO;
import br.com.gleisonandrade.bancoapi.dto.TransferenciaDTO;
import br.com.gleisonandrade.bancoapi.services.ContaService;
import br.com.gleisonandrade.bancoapi.services.ExtratoService;
import br.com.gleisonandrade.bancoapi.util.ContaExtrato;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */

@RestController
@RequestMapping("/conta")
public class ContaResource {
	@Autowired
	private ContaService contaService;
	
	@Autowired
	private ExtratoService extratoService;

	@ApiOperation(value = "Mostra a lista de contas cadastradas no sistema caso o usuário seja admin, "
			+ "demais casos são apresentadas as contas do cliente logado.", response = List.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Lista recuperada com sucesso")
	})
	@GetMapping
	public ResponseEntity<List<ContaDTO>> listar() {
		List<Conta> contas = contaService.listarTodos();
		List<ContaDTO> contasDTO = contas.stream().map(conta -> new ContaDTO(conta)).collect(Collectors.toList());

		return ResponseEntity.ok(contasDTO);
	}

	@ApiOperation(value = "Mostra os dados da conta com o id passado cadastrada no sistema caso o usuário seja admin, "
			+ "é possível visualizar qualquer conta, caso contrário só é permitida a consulta dos dados de uma conta "
			+ "pelo cliente associado a ela.", response = ContaDTO.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 404, message = "Não existe conta cadastrada no sistema com esse id")
	})
	@GetMapping("/{id}")
	public ResponseEntity<ContaDTO> buscar(@ApiParam(value = "Id da conta que deseja buscar", required = true) @PathVariable Long id) {
		Conta contaBuscada = contaService.buscar(id);
		ContaDTO contaDTO = new ContaDTO(contaBuscada);

		if (contaBuscada == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(contaDTO);
	}

	@ApiOperation(value = "Cadastra uma nova conta no sistema e cliente associado a ela.")
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Void> adicionar(@ApiParam(value = "Dados necessários para o cadastro de Conta e Cliente") @Valid @RequestBody NovaContaDTO contaDto) {
		Conta contaCadastrado = contaService.converteDTOEmEntidade(contaDto);
		contaCadastrado = contaService.cadastrar(contaCadastrado);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(contaCadastrado.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Realiza a atualização de uma conta.")
	@ApiResponses(value = {
		    @ApiResponse(code = 404, message = "Não existe conta cadastrada no sistema com esse id")
		})
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@ApiParam(value = "Id da conta que deseja atualizar", required = true) @PathVariable Long id, 
			@ApiParam(value = "Dados que podem ser atualizados da Conta") @Valid @RequestBody AtualizaContaDTO contaDto) {
		Conta contaBuscada = contaService.converteDTOEmEntidade(contaDto);
		contaBuscada.setId(id);
		contaBuscada = contaService.atualizar(contaBuscada);
		
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Realiza a deleção de uma conta.")
	@ApiResponses(value = {
		    @ApiResponse(code = 404, message = "Não existe conta cadastrada no sistema com esse id")
		})
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@ApiParam(value = "Id da conta que deseja excluir", required = true) @PathVariable Long id) {
		contaService.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Realiza o saque de dinheiro na conta.")
	@ApiResponses(value = {
	    @ApiResponse(code = 403, message = "Só é possível realizar o saque de dinheiro se você for títular da conta. "),
	    @ApiResponse(code = 404, message = "Conta inexistente, banco ou agência. "),
	    @ApiResponse(code = 422, message = "Ocorreu um erro de validação ou regra de negócio. ")
	})
	@PostMapping("/sacar")
	public ResponseEntity<Void> sacar(@ApiParam(value = "Dados necessários para realizar o saque de dinheiro de uma conta") @Valid @RequestBody SaqueDTO saqueDto) {
		ContaExtrato extrato = contaService.sacar(saqueDto);

		URI uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/{id}/extrato/{extratoId}").build()
				.expand(extrato.getContaId(), extrato.getExtratoId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Realiza o depósito de dinheiro em uma conta.")
	@ApiResponses(value = { 
			@ApiResponse(code = 404, message = "Conta inexistente, banco ou agência. "),
			@ApiResponse(code = 422, message = "Ocorreu um erro de validação. ")
	})
	@PostMapping("/depositar")
	public ResponseEntity<Void> depositar(@ApiParam(value = "Dados necessários para realizar o depósito de dinheiro de uma conta") @Valid @RequestBody DepositoDTO depositoDTO) {
		ContaExtrato extrato = contaService.depositar(depositoDTO);

		URI uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/{id}/extrato/{extratoId}").build()
				.expand(extrato.getContaId(), extrato.getExtratoId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Realiza a tranferência de dinheiro entre contas.")
	@ApiResponses(value = {
	    @ApiResponse(code = 403, message = "Só é possível transferir dinheiro se você for títular da conta. "),
	    @ApiResponse(code = 404, message = "Conta inexistente (origem ou destino), banco ou agência. "),
	    @ApiResponse(code = 422, message = "Ocorreu um erro de validação ou regra de negócio. ")
	})
	@PostMapping("/transferir")
	public ResponseEntity<Void> transferir(@ApiParam(value = "Dados necessários para realizar a tranferência de dinheiro de uma conta") @Valid @RequestBody TransferenciaDTO transferenciaDTO) {
		ContaExtrato contaExtrato = contaService.transferir(transferenciaDTO);

		URI uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/{id}/extrato/{extratoId}").build()
				.expand(contaExtrato.getContaId(), contaExtrato.getExtratoId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value = "Mostra a lista de extratos para uma conta cadastrada no sistema caso o usuário seja admin, "
			+ "demais casos são apresentados somente os extratos de contas do cliente logado.", response = ExtratoDTO.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Lista de extratos recuperada com sucesso")
	})
	@GetMapping("/{id}/extrato/")
	public ResponseEntity<List<ExtratoDTO>> extratos(@ApiParam(value = "Id da conta que se deseja obter os extratos bancários", required = true) @PathVariable Long id){
		List<Extrato> extratos = extratoService.listarTodosPorContaId(id);
		List<ExtratoDTO> extratosDTO = extratos.stream().map(extrato -> new ExtratoDTO(extrato)).collect(Collectors.toList());

		return ResponseEntity.ok(extratosDTO);
	}
	
	@ApiOperation(value = "Mostra um extrato especifico para uma conta cadastrada no sistema caso o usuário seja admin, "
			+ "demais casos o extrato só é exibido se a contaa pertencer ao cliente logado.", response = ExtratoDTO.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Extrato obtido com sucesso")
	})
	@GetMapping("/{id}/extrato/{extratoId}")
	public ResponseEntity<ExtratoDTO> extrato(@ApiParam(value = "Id da conta que se deseja obter o extrato bancários") @PathVariable Long id, 
			@ApiParam(value = "Id do extrato que se deseja obter os dados") @PathVariable Long extratoId){
		Extrato extrato = extratoService.buscar(id, extratoId);
		ExtratoDTO extratoDTO = new ExtratoDTO(extrato);

		return ResponseEntity.ok(extratoDTO);
	}	

	@ApiOperation(value = "Mostra a lista com suporte a paginação de contas cadastradas no sistema caso o usuário seja admin, "
			+ "demais casos são apresentadas as contas do cliente logado.", response = Page.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Lista recuperada com sucesso")
	})
	@GetMapping("/page")
	public ResponseEntity<Page<ContaDTO>> buscaPaginada(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Conta> list = contaService.buscaPaginada(page, linesPerPage, orderBy, direction);
		Page<ContaDTO> listDto = list.map(obj -> new ContaDTO(obj));

		return ResponseEntity.ok().body(listDto);
	}

}
