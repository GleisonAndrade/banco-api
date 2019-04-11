/**
 * 
 */
package br.com.gleisonandrade.bancoapi.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import br.com.gleisonandrade.bancoapi.domain.Agencia;
import br.com.gleisonandrade.bancoapi.domain.Banco;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
@ApiModel(description="Detalhes sobre a agência. ")
public class AgenciaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(notes = "Número da agência. ")
	@NotEmpty(message="Preenchimento obrigatório")
	@Pattern(regexp="\\d{4}-\\d{1}", message="O número da agência deve possuir o seguinte formato 0000-0 e ser formado apenas por números!")
	private String numero;
	
	@ApiModelProperty(notes = "Nome da agência. ")
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=3, max=250, message="O tamanho deve ser entre 3 e 250 caracteres")
	private String nome;
	
	@ApiModelProperty(notes = "Banco que a agência pertence. ")
	private Banco banco;
	
	public AgenciaDTO(Agencia agencia) {
		this.numero = agencia.getNumero();
		this.nome = agencia.getNome();
		this.banco = agencia.getBanco();
	}

	/**
	 * 
	 */
	public AgenciaDTO() {
		super();
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

}