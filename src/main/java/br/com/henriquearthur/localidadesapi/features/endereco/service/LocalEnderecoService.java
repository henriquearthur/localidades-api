package br.com.henriquearthur.localidadesapi.features.endereco.service;

import br.com.henriquearthur.localidadesapi.features.endereco.dto.EnderecoDTO;
import br.com.henriquearthur.localidadesapi.features.endereco.entity.Endereco;
import br.com.henriquearthur.localidadesapi.features.endereco.repository.EnderecoRepository;
import br.com.henriquearthur.localidadesapi.exception.APIRequestException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LocalEnderecoService {
    private final EnderecoRepository repository;
    private final ModelMapper modelMapper;

    public LocalEnderecoService(EnderecoRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public List<Endereco> getEnderecos() {
        log.info("Buscando endereços no banco de dados");
        return repository.findAll();
    }

    public Endereco getEndereco(String cep) throws APIRequestException {
        log.info("Buscando endereço de CEP {} no banco de dados", cep);

        return repository.findById(cep).orElseThrow(
                () -> new APIRequestException(HttpStatus.NOT_FOUND, "Endereço não encontrado"));
    }

    public Endereco saveEndereco(EnderecoDTO enderecoDTO) {
        log.info("Salvando endereço de CEP {} no banco de dados", enderecoDTO.getCep());
        return repository.save(convertDTOToEntity(enderecoDTO));
    }

    private Endereco convertDTOToEntity(EnderecoDTO dto) {
        return modelMapper.map(dto, Endereco.class);
    }
}
