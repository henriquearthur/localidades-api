package br.com.henriquearthur.localidadesapi.pais.repository;

import br.com.henriquearthur.localidadesapi.pais.entity.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisRepository extends JpaRepository<Pais, String> {
}