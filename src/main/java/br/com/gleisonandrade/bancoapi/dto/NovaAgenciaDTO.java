/**
 * 
 */
package br.com.gleisonandrade.bancoapi.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import br.com.gleisonandrade.bancoapi.domain.Agencia;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public class NovaAgenciaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Pattern(regexp="\\d{4}-\\d{1}", message="O número da agência deve possuir o seguinte formato 0000-0 e ser formado apenas por números! {0}")
	private String numero;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=3, max=250, message="O tamanho deve ser entre 3 e 250 caracteres")
	private String nome;
	
	@NotNull(message="Preenchimento obrigatório")
	private Long bancoId;	

	public NovaAgenciaDTO(Agencia agencia) {
		this.id = agencia.getId();
		this.numero = agencia.getNumero();
		this.nome = agencia.getNome();
		this.bancoId = agencia.getBanco().getId();
	}

	/**
	 * 
	 */
	public NovaAgenciaDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getBancoId() {
		return bancoId;
	}

	public void setBancoId(Long bancoId) {
		this.bancoId = bancoId;
	}
	
}
