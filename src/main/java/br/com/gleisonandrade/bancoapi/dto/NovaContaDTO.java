/**
 * 
 */
package br.com.gleisonandrade.bancoapi.dto;

import java.io.Serializable;

import javax.validation.constraints.Negative;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import br.com.gleisonandrade.bancoapi.domain.Cliente;
import br.com.gleisonandrade.bancoapi.domain.Conta;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public class NovaContaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotNull(message="Preenchimento obrigatório")
	private Long agenciaId;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Pattern(regexp="\\d{5}-\\d{1}", message="O número da conta deve possuir o seguinte formato 00000-0 e ser formado apenas por números!")
	private String numero;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String tipo;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String senha;
	
	@Negative
	@NotNull(message="Preenchimento obrigatório")
	private Double saldo;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@CPF(message="O CPF informado é inválido")
	private String cpf;
	
//	public NovaContaDTO(Conta conta) {
//		this.id = conta.getId();
//		this.numero = conta.getNumero();
//		this.tipo = conta.getTipo().name();
//		this.saldo = conta.getSaldo();
//	}	
	
	public NovaContaDTO (Conta conta, Cliente cliente) {
		this.numero = conta.getNumero();
		this.tipo = conta.getTipo().name();
		this.senha = conta.getSenha();
		this.saldo = conta.getSaldo();
		
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
	}

	public Long getAgenciaId() {
		return agenciaId;
	}

	public void setAgenciaId(Long agenciaId) {
		this.agenciaId = agenciaId;
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
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}