package br.com.henriquearthur.localidadesapi.pais.service;

import br.com.henriquearthur.localidadesapi.exception.APIRequestException;
import br.com.henriquearthur.localidadesapi.pais.dto.PaisDTO;
import br.com.henriquearthur.localidadesapi.pais.ibge.client.IBGEPaisClient;
import br.com.henriquearthur.localidadesapi.pais.ibge.model.IBGEPais;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class IBGEPaisService {
    private final IBGEPaisClient ibgeClient;

    public IBGEPaisService(IBGEPaisClient ibgeClient) {
        this.ibgeClient = ibgeClient;
    }

    public List<PaisDTO> getPaises() throws APIRequestException {
        try {
            log.info("Buscando países no IBGE");
            List<IBGEPais> paisesIBGE = ibgeClient.getPaises("nome").getBody();

            if (paisesIBGE == null) {
                log.error("Erro ao buscar países no IBGE - resposta vazia");
                throw new APIRequestException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar países no IBGE - resposta vazia");
            }

            return paisesIBGE.stream().map(this::convertIBGEPaisToDTO).toList();
        } catch (FeignException e) {
            log.error("Erro ao buscar países no IBGE", e);
            throw new APIRequestException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar países no IBGE - " + e.getMessage());
        }
    }

    public PaisDTO getPais(String codigo) throws APIRequestException {
        try {
            log.info("Buscando pais de código {} no IBGE", codigo);
            List<IBGEPais> paisesIBGE = ibgeClient.getPais(codigo).getBody();

            if (paisesIBGE == null) {
                log.error("Erro ao buscar país {} no IBGE - resposta vazia", codigo);
                throw new APIRequestException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar país " + codigo + " no IBGE - resposta vazia");
            }

            return convertIBGEPaisToDTO(paisesIBGE.get(0));
        } catch (FeignException e) {
            log.error("Erro ao buscar país no IBGE", e);
            throw new APIRequestException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar país no IBGE - " + e.getMessage());
        }
    }

    private PaisDTO convertIBGEPaisToDTO(IBGEPais pais) {
        PaisDTO dto = new PaisDTO();
        dto.setCodigo(pais.getId().getIdentificadorONU());
        dto.setNome(pais.getNome());
        dto.setSigla2Letras(pais.getId().getIdentificador2Letras());
        dto.setSigla3Letras(pais.getId().getIdentificador3Letras());
        dto.setRegiao(pais.getSubregiao().getRegiao().getNome());
        dto.setSubRegiao(pais.getSubregiao().getNome());
        dto.setRegiaoIntermediaria(Objects.nonNull(pais.getRegiaoIntermediaria()) ? pais.getRegiaoIntermediaria().getNome() : null);
        return dto;
    }
}
