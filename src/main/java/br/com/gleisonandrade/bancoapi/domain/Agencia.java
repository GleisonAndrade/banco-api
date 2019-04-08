/**
 * 
 */
package br.com.gleisonandrade.bancoapi.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */

@Entity
public class Agencia implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String numero;
	private String nome;
	
	@ManyToOne
	@JoinColumn(name="banco_id")
	private Banco banco;
	
	public Agencia() {
	}

	/**
	 * @param numero
	 * @param nome
	 * @param banco
	 * @param contas
	 */
	public Agencia(String numero, String nome, Banco banco) {
		super();
		this.numero = numero;
		this.nome = nome;
		this.banco = banco;
	}

	public Agencia(String numero, String nome) {
		super();
		this.numero = numero;
		this.nome = nome;
	}

	public Agencia(Long id) {
		this.id = id;
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

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((banco == null) ? 0 : banco.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		Agencia other = (Agencia) obj;
		if (banco == null) {
			if (other.banco != null) {
				return false;
			}
		} else if (!banco.equals(other.banco)) {
			return false;
		}
		if (numero == null) {
			if (other.numero != null) {
				return false;
			}
		} else if (!numero.equals(other.numero)) {
			return false;
		}
		return true;
	}
	
	
}