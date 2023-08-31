package br.com.henriquearthur.localidadesapi.features.estado.repository;

import br.com.henriquearthur.localidadesapi.features.estado.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {
}