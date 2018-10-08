package com.dionatan.restapp.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.dionatan.restapp.domain.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long>  {
	
	@Query(value="From Reserva where dataSaida >= CURRENT_DATE and dataEntrada <= CURRENT_DATE")
	List<Reserva> findHospedeInHotel(Date data);

	@Query(value="From Reserva where dataEntrada >= CURRENT_DATE or dataSaida < CURRENT_DATE")
	List<Reserva> findHospedeNotInHotel(Date data);
}
