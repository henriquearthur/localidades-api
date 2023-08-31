package br.com.henriquearthur.localidadesapi.features.municipio.service;

import br.com.henriquearthur.localidadesapi.exception.APIRequestException;
import br.com.henriquearthur.localidadesapi.features.municipio.dto.MunicipioDTO;
import br.com.henriquearthur.localidadesapi.features.municipio.entity.Municipio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MunicipioService {
    private final IBGEMunicipioService ibgeMunicipioService;
    private final LocalMunicipioService localMunicipioService;

    public MunicipioService(IBGEMunicipioService ibgeMunicipioService, LocalMunicipioService localMunicipioService) {
        this.ibgeMunicipioService = ibgeMunicipioService;
        this.localMunicipioService = localMunicipioService;
    }

    public List<MunicipioDTO> getMunicipiosPorEstado(Integer codigoEstado) {
        List<Municipio> municipios;

        try {
            List<MunicipioDTO> municipiosIBGE = ibgeMunicipioService.getMunicipiosPorEstado(codigoEstado);
            municipios = localMunicipioService.saveMunicipios(municipiosIBGE);
        } catch (Exception e) {
            log.warn("Erro ao buscar e salvar municípios do estado {} da API do IBGE. Buscando municípios no banco de dados local.", codigoEstado, e);
            municipios = localMunicipioService.getMunicipiosPorEstado(codigoEstado);
        }

        return municipios.stream().map(localMunicipioService::convertEntityToDTO).toList();
    }

    public MunicipioDTO getMunicipio(Integer codigo) throws APIRequestException {
        Municipio municipio;

        try {
            MunicipioDTO municipioIBGE = ibgeMunicipioService.getMunicipio(codigo);
            municipio = localMunicipioService.saveMunicipio(municipioIBGE);
        } catch (Exception e) {
            log.warn("Erro ao buscar e salvar município {} da API do IBGE. Buscando estado no banco de dados local.", codigo, e);
            municipio = localMunicipioService.getMunicipio(codigo);
        }

        return localMunicipioService.convertEntityToDTO(municipio);
    }
}
