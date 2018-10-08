package com.dionatan.restapp.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dionatan.restapp.domain.Reserva;
import com.dionatan.restapp.services.ReservaService;

@RestController
@RequestMapping(value="/reserva")
public class ReservaResouce {

	@Autowired
	private ReservaService reservaService;
	
	@RequestMapping(method=RequestMethod.POST, value="/checkin")
	public ResponseEntity<Void> checkIn(@RequestBody Reserva reserva ){
		reserva = reservaService.insert(reserva);
		
		URI  uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(reserva.getId())
				.toUri();
	
		return ResponseEntity.created(uri).build();
		
	}
	@RequestMapping(method=RequestMethod.POST, value="/checkout")
	public ResponseEntity<Void> checkOut(@RequestBody Reserva reserva ){
		reserva = reservaService.update(reserva);
		
		URI  uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(reserva.getId())
				.toUri();
	
		return ResponseEntity.created(uri).build();
		
	}
		
}
