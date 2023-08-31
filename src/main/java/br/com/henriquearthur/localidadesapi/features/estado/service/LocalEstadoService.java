package br.com.henriquearthur.localidadesapi.features.estado.service;

import br.com.henriquearthur.localidadesapi.exception.APIRequestException;
import br.com.henriquearthur.localidadesapi.features.estado.dto.EstadoDTO;
import br.com.henriquearthur.localidadesapi.features.estado.entity.Estado;
import br.com.henriquearthur.localidadesapi.features.estado.repository.EstadoRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LocalEstadoService {
    private final EstadoRepository repository;
    private final ModelMapper modelMapper;

    public LocalEstadoService(EstadoRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public List<Estado> getEstados() {
        log.info("Buscando estados no banco de dados");
        return repository.findAll();
    }

    public Estado getEstado(Integer codigo) throws APIRequestException {
        log.info("Buscando estado de código {} no banco de dados", codigo);

        return repository.findById(codigo).orElseThrow(
                () -> new APIRequestException(HttpStatus.NOT_FOUND, "Estado não encontrado"));
    }

    public Estado saveEstado(EstadoDTO estadoDTO) {
        log.info("Salvando estado de código {} no banco de dados", estadoDTO.getId());
        return repository.save(convertDTOToEntity(estadoDTO));
    }

    public List<Estado> saveEstados(List<EstadoDTO> estadosDTO) {
        log.info("Salvando lista de {} estados no banco de dados", estadosDTO.size());
        return repository.saveAll(estadosDTO.stream().map(this::convertDTOToEntity).toList());
    }

    public Estado convertDTOToEntity(EstadoDTO estadoDTO) {
        return modelMapper.map(estadoDTO, Estado.class);
    }

    public EstadoDTO convertEntityToDTO(Estado estado) {
        return modelMapper.map(estado, EstadoDTO.class);
    }
}
