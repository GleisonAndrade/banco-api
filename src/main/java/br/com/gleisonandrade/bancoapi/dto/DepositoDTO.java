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
public class DepositoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotNull(message="Preenchimento obrigatório")
	private Long bancoId;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String agenciaNumero;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Pattern(regexp="\\d{5}-\\d{1}", message="O número da conta deve possuir o seguinte formato 00000-0 e ser formado apenas por números!")
	private String contaNumero;
	
	@NotEmpty(message="Preenchimento obrigatório")
//	@Pattern(regexp="/([POUPANCA|CORRENTE])/g", message="O valor informado é inválido, era esperado CORRENTE ou POUPANCA")
	private String tipo;
	
	@Positive(message="O valor deve ser maior que zero")
	@NotNull(message="Preenchimento obrigatório")
	private Double valor;

	public Long getBancoId() {
		return bancoId;
	}

	public void setBancoId(Long bancoId) {
		this.bancoId = bancoId;
	}

	public String getAgenciaNumero() {
		return agenciaNumero;
	}

	public void setAgenciaNumero(String agenciaNumero) {
		this.agenciaNumero = agenciaNumero;
	}

	public String getContaNumero() {
		return contaNumero;
	}

	public void setContaNumero(String contaNumero) {
		this.contaNumero = contaNumero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	

}
