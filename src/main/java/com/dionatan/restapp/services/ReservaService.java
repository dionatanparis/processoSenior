package com.dionatan.restapp.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dionatan.restapp.domain.Hospede;
import com.dionatan.restapp.domain.Reserva;
import com.dionatan.restapp.dto.ReservaDTO;
import com.dionatan.restapp.repositories.HospedeRepository;
import com.dionatan.restapp.repositories.ReservaRepository;

@Service
public class ReservaService {

	@Autowired
	private HospedeRepository hospedeRepository;
	
	@Autowired ReservaRepository reservaRepository;
	
	public Hospede find(Long id, String nome, String documento) {
		Optional<Hospede> hospede = hospedeRepository.findById(id);
		return hospede.orElseThrow(() -> new com.dionatan.restapp.services.exeptions.ObjectNotFoundException("Objeto não encontrado id:" + id ) );
	}
	
	public Reserva insert(Reserva reserva) {
		return reservaRepository.save(reserva);
	}
	
	public Reserva update(Reserva reserva) {
		Reserva reservaBd = reservaRepository.findById(reserva.getId()).orElse(null);
		reservaBd.setDataSaida(reserva.getDataSaida());
		reservaBd.setValor(100);
		return reservaRepository.save(reservaBd);
	}
	
	public List<ReservaDTO> findInHotel(Date data) {
		
		List<ReservaDTO> reservas = 
				reservaRepository.findHospedeInHotel(data)
				.stream().map(obj -> new ReservaDTO(obj))
				.collect(Collectors.toList());
		
		reservas.forEach(reserva -> calculaValor(reserva));
		
		
		return reservas;
	}
	
	public List<Reserva> findNotInHotel(Date data) {
		return reservaRepository.findHospedeNotInHotel(data);
	}
	
	
	private  void calculaValor(ReservaDTO reserva) {
		
		LocalDate dataIni = Instant.ofEpochMilli(reserva.getDataEntrada().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate dataFim = Instant.ofEpochMilli(reserva.getDataSaida().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		List<LocalDate> dates = new ArrayList<>();
		
		for(LocalDate ld = dataIni; !ld.isAfter(dataFim); ld = ld.plusDays(1)) {
			dates.add(ld);
		}
		
		int diariasFds =  dates
				.stream()
				.filter(d -> (d.getDayOfWeek().ordinal() == 6  || d.getDayOfWeek().ordinal() == 7 ))
				.collect(Collectors.toList())
				.size();
		
		int diariasSemana =  dates
				.stream()
				.filter(d -> (d.getDayOfWeek().ordinal() != 6  && d.getDayOfWeek().ordinal() != 7 )).
				collect(Collectors.toList())
				.size();
		
		reserva.setValor((diariasFds* 150)+(diariasSemana* 120));
		
	}
	
	
}
