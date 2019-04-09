/**
 * 
 */
package br.com.gleisonandrade.bancoapi.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.gleisonandrade.bancoapi.domain.enuns.TipoDeConta;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */

@Entity
public class Conta implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String numero;
	
	@Enumerated(EnumType.STRING)
	private TipoDeConta tipo;	
	private Double saldo = 0.0;	
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="agencia_id")
	private Agencia agencia;
	
	public Conta() {
	}

	/**
	 * @param numero
	 * @param tipo
	 * @param cliente
	 * @param agencia
	 * @param saldo
	 */
	public Conta(String numero, TipoDeConta tipo, Cliente cliente, Agencia agencia, Double saldo) {
		super();
		this.numero = numero;
		this.tipo = tipo;
		this.cliente = cliente;
		this.agencia = agencia;
		this.saldo = saldo;
	}


	/**
	 * @param numero
	 * @param tipo
	 * @param saldo
	 */
	public Conta(String numero, TipoDeConta tipo, Double saldo) {
		super();
		this.numero = numero;
		this.tipo = tipo;
		this.saldo = saldo;
	}

	/**
	 * @param id
	 * @param numero
	 * @param tipo
	 * @param saldo
	 */
	public Conta(Long id, String numero, TipoDeConta tipo, Double saldo) {
		super();
		this.numero = numero;
		this.tipo = tipo;
		this.saldo = saldo;
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

	public TipoDeConta getTipo() {
		return tipo;
	}

	public void setTipo(TipoDeConta tipo) {
		this.tipo = tipo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agencia == null) ? 0 : agencia.hashCode());
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
		Conta other = (Conta) obj;
		if (agencia == null) {
			if (other.agencia != null) {
				return false;
			}
		} else if (!agencia.equals(other.agencia)) {
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