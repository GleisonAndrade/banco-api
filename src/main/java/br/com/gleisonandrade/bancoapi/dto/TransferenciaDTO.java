/**
 * 
 */
package br.com.gleisonandrade.bancoapi.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public class TransferenciaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotNull(message="Preenchimento obrigatório")
	private Long bancoOrigemId;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String agenciaOrigemNumero;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Pattern(regexp="\\d{5}-\\d{1}", message="O número da conta deve possuir o seguinte formato 00000-0 e ser formado apenas por números!")
	private String contaOrigemNumero;
	
	@NotEmpty(message="Preenchimento obrigatório")
//	@Pattern(regexp="/([POUPANCA|CORRENTE])/g", message="O valor informado é inválido, era esperado CORRENTE ou POUPANCA")
	private String tipoOrigem;
	
	
	@NotNull(message="Preenchimento obrigatório")
	private Long bancoDestinoId;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String agenciaDestinoNumero;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Pattern(regexp="\\d{5}-\\d{1}", message="O número da conta deve possuir o seguinte formato 00000-0 e ser formado apenas por números!")
	private String contaDestinoNumero;
	
	@NotEmpty(message="Preenchimento obrigatório")
//	@Pattern(regexp="/([POU
	private String tipoDestino;	
	
	@Positive(message="O valor deve ser maior que zero")
	@NotNull(message="Preenchimento obrigatório")
	private Double valor;

	public Long getBancoOrigemId() {
		return bancoOrigemId;
	}

	public void setBancoOrigemId(Long bancoOrigemId) {
		this.bancoOrigemId = bancoOrigemId;
	}

	public String getAgenciaOrigemNumero() {
		return agenciaOrigemNumero;
	}

	public void setAgenciaOrigemNumero(String agenciaOrigemNumero) {
		this.agenciaOrigemNumero = agenciaOrigemNumero;
	}

	public String getContaOrigemNumero() {
		return contaOrigemNumero;
	}

	public void setContaOrigemNumero(String contaOrigemNumero) {
		this.contaOrigemNumero = contaOrigemNumero;
	}

	public String getTipoOrigem() {
		return tipoOrigem;
	}

	public void setTipoOrigem(String tipoOrigem) {
		this.tipoOrigem = tipoOrigem;
	}

	public Long getBancoDestinoId() {
		return bancoDestinoId;
	}

	public void setBancoDestinoId(Long bancoDestinoId) {
		this.bancoDestinoId = bancoDestinoId;
	}

	public String getAgenciaDestinoNumero() {
		return agenciaDestinoNumero;
	}

	public void setAgenciaDestinoNumero(String agenciaDestinoNumero) {
		this.agenciaDestinoNumero = agenciaDestinoNumero;
	}

	public String getContaDestinoNumero() {
		return contaDestinoNumero;
	}

	public void setContaDestinoNumero(String contaDestinoNumero) {
		this.contaDestinoNumero = contaDestinoNumero;
	}

	public String getTipoDestino() {
		return tipoDestino;
	}

	public void setTipoDestino(String tipoDestino) {
		this.tipoDestino = tipoDestino;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
}
