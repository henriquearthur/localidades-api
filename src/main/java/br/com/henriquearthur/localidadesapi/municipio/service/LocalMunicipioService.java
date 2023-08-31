package br.com.henriquearthur.localidadesapi.municipio.service;


import br.com.henriquearthur.localidadesapi.exception.APIRequestException;
import br.com.henriquearthur.localidadesapi.municipio.dto.MunicipioDTO;
import br.com.henriquearthur.localidadesapi.municipio.entity.Municipio;
import br.com.henriquearthur.localidadesapi.municipio.repository.MunicipioRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LocalMunicipioService {
    private final MunicipioRepository repository;
    private final ModelMapper modelMapper;

    public LocalMunicipioService(MunicipioRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public List<Municipio> getMunicipiosPorEstado(Integer codigoEstado) {
        log.info("Buscando municípios do estado de código {} no banco de dados", codigoEstado);
        return repository.findAllByEstadoId(codigoEstado);
    }

    public Municipio getMunicipio(Integer codigo) throws APIRequestException {
        log.info("Buscando município de código {} no banco de dados", codigo);

        return repository.findById(codigo).orElseThrow(
                () -> new APIRequestException(HttpStatus.NOT_FOUND, "Município não encontrado"));
    }

    public Municipio saveMunicipio(MunicipioDTO municipioDTO) {
        log.info("Salvando município de código {} no banco de dados", municipioDTO.getId());
        return repository.save(convertDTOToEntity(municipioDTO));
    }

    public List<Municipio> saveMunicipios(List<MunicipioDTO> municipiosDTO) {
        log.info("Salvando lista de {} municípios no banco de dados", municipiosDTO.size());
        return repository.saveAll(municipiosDTO.stream().map(this::convertDTOToEntity).toList());
    }

    public Municipio convertDTOToEntity(MunicipioDTO municipioDTO) {
        return modelMapper.map(municipioDTO, Municipio.class);
    }

    public MunicipioDTO convertEntityToDTO(Municipio municipio) {
        return modelMapper.map(municipio, MunicipioDTO.class);
    }
}
