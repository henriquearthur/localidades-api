package br.com.henriquearthur.localidadesapi.estado.service;

import br.com.henriquearthur.localidadesapi.estado.dto.EstadoDTO;
import br.com.henriquearthur.localidadesapi.estado.ibge.client.IBGEEstadoClient;
import br.com.henriquearthur.localidadesapi.estado.ibge.model.IBGEEstado;
import br.com.henriquearthur.localidadesapi.exception.APIRequestException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class IBGEEstadoService {
    private final IBGEEstadoClient ibgeClient;
    private final ModelMapper modelMapper;

    public IBGEEstadoService(IBGEEstadoClient ibgeClient, ModelMapper modelMapper) {
        this.ibgeClient = ibgeClient;
        this.modelMapper = modelMapper;
    }

    public List<EstadoDTO> getEstados() throws APIRequestException {
        try {
            log.info("Buscando estados no IBGE");
            List<IBGEEstado> estadosIBGE = ibgeClient.getEstados("nome").getBody();

            if (estadosIBGE == null) {
                log.error("Erro ao buscar estados no IBGE - resposta vazia");
                throw new APIRequestException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar estados no IBGE - resposta vazia");
            }

            return estadosIBGE.stream().map(this::convertIBGEEstadoToDTO).toList();
        } catch (FeignException e) {
            log.error("Erro ao buscar estados no IBGE", e);
            throw new APIRequestException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar estados no IBGE - " + e.getMessage());
        }
    }

    public EstadoDTO getEstado(Integer codigo) throws APIRequestException {
        try {
            log.info("Buscando estado de código {} no IBGE", codigo);
            IBGEEstado estadoIBGE = ibgeClient.getEstado(codigo).getBody();

            if (estadoIBGE == null) {
                log.error("Erro ao buscar estado {} no IBGE - resposta vazia", codigo);
                throw new APIRequestException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar estado " + codigo + " no IBGE - resposta vazia");
            }

            return convertIBGEEstadoToDTO(estadoIBGE);
        } catch (FeignException e) {
            log.error("Erro ao buscar estado no IBGE", e);
            throw new APIRequestException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar estado no IBGE - " + e.getMessage());
        }
    }

    public EstadoDTO convertIBGEEstadoToDTO(IBGEEstado estado) {
        return modelMapper.map(estado, EstadoDTO.class);
    }
}
