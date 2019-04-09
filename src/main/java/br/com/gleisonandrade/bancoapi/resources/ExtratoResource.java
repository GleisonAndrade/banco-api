/**
 * 
 */
package br.com.gleisonandrade.bancoapi.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	
	@GetMapping("/conta/{id}/extrato")
	public ResponseEntity<List<ExtratoDTO>> listar(@Valid @PathVariable Long id){//, 
//			@RequestParam(value="dataInicio", defaultValue = DataUtil.dataParaString(new Date())) @DateTimeFormat(pattern=DataUtil.FORMATO_PADRAO) Date dataInicio,
//			@RequestParam(value="dataFim", defaultValue = DataUtil.dataParaString(new Date())) @DateTimeFormat(pattern=DataUtil.FORMATO_PADRAO) Date dataFim) {
		
		List<Extrato> extratos = extratoService.listarTodosPorContaId(id);
		List<ExtratoDTO> extratosDTO = extratos.stream().map(extrato -> new ExtratoDTO(extrato)).collect(Collectors.toList());

		return ResponseEntity.ok(extratosDTO);
	}
	
//	@GetMapping("/conta/{id}/extrato/{extratoId}")
//	public ResponseEntity<Void> buscar(@Valid @PathVariable Long id, 
//			@RequestParam(value = "page", defaultValue = "0") Integer page) {
//		return ResponseEntity.created(null).build();
//	}
//	
//	@GetMapping(path = "/page")
//	public ResponseEntity<Page<ContaDTO>> buscaPaginada(@RequestParam(value = "page", defaultValue = "0") Integer page,
//			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
//			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
//			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
//
//		Page<Extrato> list = extratoService.buscaPaginada(page, linesPerPage, orderBy, direction);
//		Page<ExtratoDTO> listDto = list.map(obj -> new ExtratoDTO(obj));
//
//		return ResponseEntity.ok().body(listDto);
//	}
}
