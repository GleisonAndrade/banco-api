/**
 * 
 */
package br.com.gleisonandrade.bancoapi.dto;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import br.com.gleisonandrade.bancoapi.domain.Cliente;
import br.com.gleisonandrade.bancoapi.domain.enuns.Perfil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */

@ApiModel(description="Detalhes sobre o cliente. ")
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(notes = "ID do cliente gerado automáticamente pelo banco de dado.")
	@NotNull(message="Preenchimento obrigatório")	
	private Long id;
	
	@ApiModelProperty(notes = "Nome do cliente. ")
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	
	@ApiModelProperty(notes = "Cpf do cliente. ")
	@NotEmpty(message="Preenchimento obrigatório")
	@CPF(message="O CPF informado é inválido")
	private String cpf;
	
	@ApiModelProperty(notes = "Perfis de cliente. ")
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
