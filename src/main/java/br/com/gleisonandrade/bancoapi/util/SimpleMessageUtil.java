/**
 * 
 */
package br.com.gleisonandrade.bancoapi.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Header;
import springfox.documentation.service.ResponseMessage;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
@Component
public class SimpleMessageUtil {
	
	private final ResponseMessage m201 = customMessageLocation(null);
	private final ResponseMessage m204put = simpleMessage(204, "Sucesso ao atualizar recurso");
	private final ResponseMessage m204del = simpleMessage(204, "Sucesso ao excluir recurso");
	
	private final ResponseMessage m403 = simpleMessage(403, "O recurso que você estava tentando acessar é proibido");
	private final ResponseMessage m404 = simpleMessage(404, "O recurso que você estava tentando acessar não foi encontrado");
	private final ResponseMessage m422 = simpleMessage(422, "Ocorreu um erro de validação");
	private final ResponseMessage m500 = simpleMessage(500, "Ocorreu um erro inesperado no sistema");
	
	private ResponseMessage simpleMessage(int code, String msg) {
		return new ResponseMessageBuilder().code(code).message(msg).build();
	}
    
    private ResponseMessage customMessageLocation(String mensagemPersonalizada) {
		
		Map<String, Header> map = new HashMap<>();
		map.put("location", new Header("location", "URI do novo recurso", new ModelRef("string")));
	
		return new ResponseMessageBuilder()
		.code(201)
		.message(mensagemPersonalizada != null ? mensagemPersonalizada : "Recurso criado")
		.headersWithDescription(map)
		.build();
	}

	public List<ResponseMessage> getGlobalResponseMessage(RequestMethod method) {
		if(RequestMethod.GET.equals(method)) {
			return Arrays.asList(m403, m404, m500);
		}else if(RequestMethod.POST.equals(method)) {
			return Arrays.asList(m201, m403, m422, m500);
		}else if(RequestMethod.PUT.equals(method)) {
			return Arrays.asList(m204put, m403, m404, m422, m500);
		}else if(RequestMethod.DELETE.equals(method)) {
			return Arrays.asList(m204del, m403, m404, m500);
		}
		
		return null;
	}
    
    
}
