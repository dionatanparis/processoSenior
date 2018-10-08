package com.dionatan.restapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dionatan.restapp.domain.Hospede;
import com.dionatan.restapp.repositories.HospedeRepository;

@Service 
public class HospedeService {

	@Autowired
	private HospedeRepository hospedeRepository;
	
	public Hospede find(Long id) {
		Optional<Hospede> hospede = hospedeRepository.findById(id);
		return hospede.orElseThrow(() -> new com.dionatan.restapp.services.exeptions.ObjectNotFoundException("Objeto não encontrado id:" + id ) );
	}
	
	public Hospede insert(Hospede hospede) {
		return hospedeRepository.save(hospede);
	}
	
	public Hospede update(Hospede hospede) {
		find(hospede.getId());
		return hospedeRepository.save(hospede);
	}
	
	public void delete(Long id) {
		find(id);
		hospedeRepository.deleteById(id);
	}
	
	public List<Hospede> findALl(){
		return hospedeRepository.findAll();
	}
	
//	public List<Hospede> findInHotel(){
//		
//	}
	
}
