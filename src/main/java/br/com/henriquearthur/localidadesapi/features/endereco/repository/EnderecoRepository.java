package br.com.henriquearthur.localidadesapi.features.endereco.repository;

import br.com.henriquearthur.localidadesapi.features.endereco.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, String> {
}