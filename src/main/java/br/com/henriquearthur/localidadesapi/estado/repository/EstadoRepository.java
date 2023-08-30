package br.com.henriquearthur.localidadesapi.estado.repository;

import br.com.henriquearthur.localidadesapi.estado.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {
}