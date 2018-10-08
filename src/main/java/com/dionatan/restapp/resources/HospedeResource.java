package com.dionatan.restapp.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dionatan.restapp.domain.Hospede;
import com.dionatan.restapp.domain.Reserva;
import com.dionatan.restapp.dto.ReservaDTO;
import com.dionatan.restapp.services.HospedeService;
import com.dionatan.restapp.services.ReservaService;

@RestController
@RequestMapping(value="/hospede")
public class HospedeResource {

	@Autowired
	private HospedeService hospedeService;
	
	@Autowired
	private ReservaService reservaService;
	
	@RequestMapping(value="/{id}", method= RequestMethod.GET)
	public ResponseEntity<Hospede> listar(@PathVariable Long id) {
		Hospede hospede = hospedeService.find(id);
		return ResponseEntity.ok(hospede); 
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Hospede hospede){
		hospede = hospedeService.insert(hospede);
		URI  uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(hospede.getId())
				.toUri();
	
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Long id,@RequestBody Hospede hospede){
		hospede = hospedeService.update(hospede);
		return  ResponseEntity.noContent().build();
	} 
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		hospedeService.delete(id.longValue());
		return  ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(method= RequestMethod.GET)
	public ResponseEntity<List<Hospede>> listAll() {
		List<Hospede> tarefas = hospedeService.findALl();
		return ResponseEntity.ok(tarefas); 
	}
	

	@RequestMapping(method= RequestMethod.GET, value="/inhotel")
	public ResponseEntity<List<ReservaDTO>> listInHotel() {
	
		Date data = new Date();
		return ResponseEntity.ok(reservaService.findInHotel(data)); 
	}
	
	@RequestMapping(method= RequestMethod.GET, value="/notinhotel")
	public ResponseEntity<List<Hospede>> listnotInHotel() {
	
		Date data = new Date();
		List<Hospede> hospedes = new ArrayList<>();
		reservaService.findNotInHotel(data).forEach(reserva -> hospedes.add(reserva.getHospede()));
		return ResponseEntity.ok(hospedes); 
	}
	
	
}
