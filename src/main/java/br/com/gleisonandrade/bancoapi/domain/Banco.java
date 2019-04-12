/**
 * 
 */
package br.com.gleisonandrade.bancoapi.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */

@ApiModel(description="Entidade Banco do sistema. ")
@Entity
public class Banco implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(notes = "Id do banco que a conta pertence. ")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ApiModelProperty(notes = "Nome do Banco. ")
	private String nome;
	
	public Banco() {
	}
	
	/**
	 * @param id
	 * @param nome
	 */
	public Banco(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	/**
	 * @param nome
	 */
	public Banco(String nome) {
		super();
		this.nome = nome;
	}

	public Banco(Long id) {
		super();
		this.id = id;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Banco other = (Banco) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}