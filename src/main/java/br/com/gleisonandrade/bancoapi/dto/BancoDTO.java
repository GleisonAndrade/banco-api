/**
 * 
 */
package br.com.gleisonandrade.bancoapi.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.gleisonandrade.bancoapi.domain.Banco;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public class BancoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=3, max=250, message="O tamanho deve ser entre 3 e 250 caracteres")
	private String nome;

	public BancoDTO() {
	}

	public BancoDTO(Banco banco) {
		this.id = banco.getId();
		this.nome = banco.getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}