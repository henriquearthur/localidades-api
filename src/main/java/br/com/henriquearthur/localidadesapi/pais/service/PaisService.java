package br.com.henriquearthur.localidadesapi.pais.service;

import br.com.henriquearthur.localidadesapi.exception.APIRequestException;
import br.com.henriquearthur.localidadesapi.pais.dto.PaisDTO;
import br.com.henriquearthur.localidadesapi.pais.entity.Pais;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PaisService {
    private final IBGEPaisService ibgePaisService;
    private final LocalPaisService localPaisService;

    public PaisService(IBGEPaisService ibgePaisService, LocalPaisService localPaisService) {
        this.ibgePaisService = ibgePaisService;
        this.localPaisService = localPaisService;
    }

    public List<PaisDTO> getPaises() {
        List<Pais> paises;

        try {
            List<PaisDTO> paisesIBGE = ibgePaisService.getPaises();
            paises = localPaisService.savePaises(paisesIBGE);
        } catch (Exception e) {
            log.warn("Erro ao buscar e salvar países da API do IBGE. Buscando países no banco de dados local.", e);
            paises = localPaisService.getPaises();
        }

        return paises.stream().map(localPaisService::convertEntityToDTO).toList();
    }

    public PaisDTO getPais(String codigo) throws APIRequestException {
        Pais pais;

        try {
            PaisDTO paisIBGE = ibgePaisService.getPais(codigo);
            pais = localPaisService.savePais(paisIBGE);
        } catch (Exception e) {
            log.warn("Erro ao buscar e salvar país {} da API do IBGE. Buscando país no banco de dados local.", codigo, e);
            pais = localPaisService.getPais(codigo);
        }

        return localPaisService.convertEntityToDTO(pais);
    }
}
