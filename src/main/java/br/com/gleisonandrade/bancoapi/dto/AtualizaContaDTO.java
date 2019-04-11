/**
 * 
 */
package br.com.gleisonandrade.bancoapi.dto;

import java.io.Serializable;

import javax.validation.constraints.Negative;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.gleisonandrade.bancoapi.domain.Conta;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
@ApiModel(description="Todos os detalhes sobre a Conta que podem ser atualizados. ")
public class AtualizaContaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(notes = "Número da conta. ")
	@NotEmpty(message="Preenchimento obrigatório")
	@Pattern(regexp="\\d{5}-\\d{1}", message="O número da conta deve possuir o seguinte formato 00000-0 e ser formado apenas por números!")
	private String numero;
	
	@ApiModelProperty(notes = "O tipo de conta. (CORRENTE ou POUPANCA)")
	@NotEmpty(message="Preenchimento obrigatório")
	private String tipo;
	
	@ApiModelProperty(notes = "Saldo da conta. ")
	@Negative
	@NotNull(message="Preenchimento obrigatório")
	private Double saldo;
	
	public AtualizaContaDTO(Conta conta) {
		this.numero = conta.getNumero();
		this.tipo = conta.getTipo().name();
		this.saldo = conta.getSaldo();
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

}
