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

    public List<PaisDTO> getPaises() throws APIRequestException {
        List<PaisDTO> paisesIBGE = ibgePaisService.getPaises();
        List<Pais> paises = localPaisService.savePaises(paisesIBGE);

        return paises.stream().map(localPaisService::convertEntityToDTO).toList();
    }

    public PaisDTO getPais(String codigo) throws APIRequestException {
        PaisDTO paisIBGE = ibgePaisService.getPais(codigo);
        Pais pais = localPaisService.savePais(paisIBGE);

        return localPaisService.convertEntityToDTO(pais);
    }
}
