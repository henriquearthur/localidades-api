package br.com.henriquearthur.localidadesapi.features.estado.service;

import br.com.henriquearthur.localidadesapi.exception.APIRequestException;
import br.com.henriquearthur.localidadesapi.features.estado.dto.EstadoDTO;
import br.com.henriquearthur.localidadesapi.features.estado.entity.Estado;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EstadoService {
    private final IBGEEstadoService ibgeEstadoService;
    private final LocalEstadoService localEstadoService;

    public EstadoService(IBGEEstadoService ibgeEstadoService, LocalEstadoService localEstadoService) {
        this.ibgeEstadoService = ibgeEstadoService;
        this.localEstadoService = localEstadoService;
    }

    public List<EstadoDTO> getEstados() {
        List<Estado> estados;

        try {
            List<EstadoDTO> estadosIBGE = ibgeEstadoService.getEstados();
            estados = localEstadoService.saveEstados(estadosIBGE);
        } catch (Exception e) {
            log.warn("Erro ao buscar e salvar estados da API do IBGE. Buscando estados no banco de dados local.", e);
            estados = localEstadoService.getEstados();
        }

        return estados.stream().map(localEstadoService::convertEntityToDTO).toList();
    }

    public EstadoDTO getEstado(Integer codigo) throws APIRequestException {
        Estado estado;

        try {
            EstadoDTO estadoIBGE = ibgeEstadoService.getEstado(codigo);
            estado = localEstadoService.saveEstado(estadoIBGE);
        } catch (Exception e) {
            log.warn("Erro ao buscar e salvar estado {} da API do IBGE. Buscando estado no banco de dados local.", codigo, e);
            estado = localEstadoService.getEstado(codigo);
        }

        return localEstadoService.convertEntityToDTO(estado);
    }
}
