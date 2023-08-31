package br.com.henriquearthur.localidadesapi.features.pais.repository;

import br.com.henriquearthur.localidadesapi.features.pais.entity.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisRepository extends JpaRepository<Pais, String> {
}