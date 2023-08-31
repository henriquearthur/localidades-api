package br.com.henriquearthur.localidadesapi.features.pais.service;

import br.com.henriquearthur.localidadesapi.exception.APIRequestException;
import br.com.henriquearthur.localidadesapi.features.pais.dto.PaisDTO;
import br.com.henriquearthur.localidadesapi.features.pais.entity.Pais;
import br.com.henriquearthur.localidadesapi.features.pais.repository.PaisRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LocalPaisService {
    private final PaisRepository repository;
    private final ModelMapper modelMapper;

    public LocalPaisService(PaisRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public List<Pais> getPaises() {
        log.info("Buscando países no banco de dados");
        return repository.findAll();
    }

    public Pais getPais(String codigo) throws APIRequestException {
        log.info("Buscando país de código {} no banco de dados", codigo);

        return repository.findById(codigo).orElseThrow(
                () -> new APIRequestException(HttpStatus.NOT_FOUND, "País não encontrado"));
    }

    public Pais savePais(PaisDTO paisDTO) {
        log.info("Salvando país de código {} no banco de dados", paisDTO.getCodigo());
        return repository.save(convertDTOToEntity(paisDTO));
    }

    public List<Pais> savePaises(List<PaisDTO> paisesDTO) {
        log.info("Salvando lista de {} países no banco de dados", paisesDTO.size());
        return paisesDTO.stream().map(this::savePais).toList();
    }

    public Pais convertDTOToEntity(PaisDTO paisDTO) {
        return modelMapper.map(paisDTO, Pais.class);
    }

    public PaisDTO convertEntityToDTO(Pais pais) {
        return modelMapper.map(pais, PaisDTO.class);
    }

    private boolean isPaisDTOEqualToEntity(PaisDTO dto, Pais entity) {
        return entity.getCodigo().equals(dto.getCodigo()) &&
                entity.getNome().equals(dto.getNome()) &&
                entity.getSigla2Letras().equals(dto.getSigla2Letras()) &&
                entity.getSigla3Letras().equals(dto.getSigla3Letras()) &&
                entity.getRegiao().equals(dto.getRegiao()) &&
                entity.getSubRegiao().equals(dto.getSubRegiao()) &&
                entity.getRegiaoIntermediaria().equals(dto.getRegiaoIntermediaria());
    }
}
