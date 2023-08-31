package br.com.henriquearthur.localidadesapi.features.municipio.repository;

import br.com.henriquearthur.localidadesapi.features.municipio.entity.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {
    List<Municipio> findAllByEstadoId(Integer estadoId);
}