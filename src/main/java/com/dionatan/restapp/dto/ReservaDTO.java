package com.dionatan.restapp.dto;

import java.io.Serializable;
import java.util.Date;

import com.dionatan.restapp.domain.Hospede;
import com.dionatan.restapp.domain.Reserva;

public class ReservaDTO implements Serializable {

	private Long id;
	private Hospede hospede;
	private Date dataEntrada;
	private Date dataSaida;
	private boolean adicionalVeiculo;
	private float valor;
	private float valorUltimaHospedagem;
	
	public ReservaDTO() {
	}
	
	public ReservaDTO(Reserva obj) {
		id = obj.getId();
		hospede = obj.getHospede();
		dataEntrada= obj.getDataEntrada();
		dataSaida = obj.getDataSaida();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Hospede getHospede() {
		return hospede;
	}

	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public float getValorUltimaHospedagem() {
		return valorUltimaHospedagem;
	}

	public void setValorUltimaHospedagem(float valorUltimaHospedagem) {
		this.valorUltimaHospedagem = valorUltimaHospedagem;
	}
	
	
	
}
