/**
 * 
 */
package br.com.gleisonandrade.bancoapi.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */

@RestController
@RequestMapping("/cliente")
public class ClienteResource {
	
//	@Autowired
//	private ClienteService clienteService;
//	
//	@GetMapping("/{id}/conta")
//	public ResponseEntity<List<ClienteDTO>> buscarClientes(@PathVariable Long id) {
//		List<Cliente> clientes = clienteService.buscarPorBanco(id);
//		List<ClienteDTO> clientesDTO = clientes.stream().map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList());
//
//		return ResponseEntity.ok(clientesDTO);
//	}
//	
//	@GetMapping(path = "/{id}/conta/{id}")
//	public ResponseEntity<ClienteDTO> buscarCliente(@PathVariable Long id, @PathVariable String numero) {
//		Cliente clienteBuscada = clienteService.buscarPorNumero(id, numero);
//
//		if (clienteBuscada == null) {
//			return ResponseEntity.notFound().build();
//		}
//
//		return ResponseEntity.ok(new ClienteDTO(clienteBuscada));
//	}
}