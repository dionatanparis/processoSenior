package com.dionatan.restapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dionatan.restapp.domain.Hospede;

@Repository
public interface HospedeRepository extends JpaRepository<Hospede, Long> {
	
	List<Hospede> findByNome(String nome);
	List<Hospede> findByDocumento(String documento);
	List<Hospede> findByTelefone(String telefone);
}
