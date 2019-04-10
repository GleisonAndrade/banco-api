/**
 * 
 */
package br.com.gleisonandrade.bancoapi.dto;

import java.io.Serializable;
import java.util.Set;

import br.com.gleisonandrade.bancoapi.domain.Cliente;
import br.com.gleisonandrade.bancoapi.domain.enuns.Perfil;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String cpf;
	private Set<Perfil> perfies;

	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.perfies = cliente.getPerfis();
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Set<Perfil> getPerfies() {
		return perfies;
	}

	public void setPerfies(Set<Perfil> perfies) {
		this.perfies = perfies;
	}
	
}
