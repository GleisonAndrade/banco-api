/**
 * 
 */
package br.com.gleisonandrade.bancoapi.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.gleisonandrade.bancoapi.domain.Conta;
import br.com.gleisonandrade.bancoapi.domain.Extrato;
import io.swagger.annotations.ApiModel;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
@ApiModel(description="Todos os dados sobre o extrato de uma Conta. ")
public class ExtratoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date data;
	private String tipoOperacao;
	private Double valor;
	private String informacoes;
	
	private String agenciaNumero;
	private String contaNumero;
	private String titular;

	public ExtratoDTO(Extrato extrato) {
		this.id = extrato.getId();
		this.data = extrato.getData().getTime();
		this.tipoOperacao = extrato.getTipoOperacao().name();
		this.valor = extrato.getValor();
		this.informacoes = extrato.getInformacoes();
		inicializaCampos(extrato.getConta());
	}

	/**
	 * @param conta
	 */
	private void inicializaCampos(Conta conta) {
		if(conta == null) {
			this.agenciaNumero = "";
			this.contaNumero = "";
			this.titular = "";
			return;
		}
		
		this.contaNumero = conta.getNumero();
		
		if (conta.getAgencia() != null) {
			this.agenciaNumero = conta.getAgencia().getNumero();
		}
		
		if(conta.getCliente() != null) {
			this.titular = conta.getCliente().getNome();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getString() {
		return tipoOperacao;
	}

	public void setString(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}

	public String getAgenciaNumero() {
		return agenciaNumero;
	}

	public void setAgenciaNumero(String agenciaNumero) {
		this.agenciaNumero = agenciaNumero;
	}

	public String getContaNumero() {
		return contaNumero;
	}

	public void setContaNumero(String contaNumero) {
		this.contaNumero = contaNumero;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

}
