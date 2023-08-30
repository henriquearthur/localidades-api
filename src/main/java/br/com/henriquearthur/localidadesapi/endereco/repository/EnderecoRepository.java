package br.com.henriquearthur.localidadesapi.endereco.repository;

import br.com.henriquearthur.localidadesapi.endereco.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, String> {
}