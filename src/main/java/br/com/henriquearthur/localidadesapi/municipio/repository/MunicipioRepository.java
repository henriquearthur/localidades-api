package br.com.henriquearthur.localidadesapi.municipio.repository;

import br.com.henriquearthur.localidadesapi.municipio.entity.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {
    List<Municipio> findAllByEstadoId(Integer estadoId);
}