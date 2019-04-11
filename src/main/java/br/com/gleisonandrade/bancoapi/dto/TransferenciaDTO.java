/**
 * 
 */
package br.com.gleisonandrade.bancoapi.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
@ApiModel(description="Todos os dados sobre a Conta necessários para realizar transferência. ")
public class TransferenciaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(notes = "Id do banco que a conta de origem pertence. ")
	@NotNull(message="Preenchimento obrigatório")
	private Long bancoOrigemId;
	
	@ApiModelProperty(notes = "Número da Agencia que a conta de origem pertence. ")
	@NotEmpty(message="Preenchimento obrigatório")
	private String agenciaOrigemNumero;
	
	@ApiModelProperty(notes = "Número da conta de origem. ")
	@NotEmpty(message="Preenchimento obrigatório")
	@Pattern(regexp="\\d{5}-\\d{1}", message="O número da conta deve possuir o seguinte formato 00000-0 e ser formado apenas por números!")
	private String contaOrigemNumero;
	
	@ApiModelProperty(notes = "O tipo de conta de origem. (CORRENTE ou POUPANCA)")
	@NotEmpty(message="Preenchimento obrigatório")
//	@Pattern(regexp="/([POUPANCA|CORRENTE])/g", message="O valor informado é inválido, era esperado CORRENTE ou POUPANCA")
	private String tipoOrigem;
	
	/** 
	 * 
	 *  CONTA DESTINO
	 * 
	 * **/
	
	@ApiModelProperty(notes = "Id do banco que a conta destino pertence. ")
	@NotNull(message="Preenchimento obrigatório")
	private Long bancoDestinoId;
	
	@ApiModelProperty(notes = "Número da Agencia que a conta de destino pertence. ")
	@NotEmpty(message="Preenchimento obrigatório")
	private String agenciaDestinoNumero;
	
	@ApiModelProperty(notes = "Número da conta de destino. ")
	@NotEmpty(message="Preenchimento obrigatório")
	@Pattern(regexp="\\d{5}-\\d{1}", message="O número da conta deve possuir o seguinte formato 00000-0 e ser formado apenas por números!")
	private String contaDestinoNumero;
	
	@ApiModelProperty(notes = "O tipo de conta de destino. (CORRENTE ou POUPANCA)")
	@NotEmpty(message="Preenchimento obrigatório")
//	@Pattern(regexp="/([POU
	private String tipoDestino;	
	
	@ApiModelProperty(notes = "Valor que será transferido da conta de origem. ")
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
