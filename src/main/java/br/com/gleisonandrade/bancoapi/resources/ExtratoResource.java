/**
 * 
 */
package br.com.gleisonandrade.bancoapi.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.gleisonandrade.bancoapi.domain.Extrato;
import br.com.gleisonandrade.bancoapi.dto.ExtratoDTO;
import br.com.gleisonandrade.bancoapi.services.ExtratoService;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */

@RestController
public class ExtratoResource {
	
	@Autowired
	private ExtratoService extratoService;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/conta/{id}/extrato")
	public ResponseEntity<List<ExtratoDTO>> listar(@Valid @PathVariable Long id){
		List<Extrato> extratos = extratoService.listarTodosPorContaId(id);
		List<ExtratoDTO> extratosDTO = extratos.stream().map(extrato -> new ExtratoDTO(extrato)).collect(Collectors.toList());

		return ResponseEntity.ok(extratosDTO);
	}
	
}
