package br.com.henriquearthur.localidadesapi.features.municipio.service;

import br.com.henriquearthur.localidadesapi.exception.APIRequestException;
import br.com.henriquearthur.localidadesapi.features.estado.dto.EstadoDTO;
import br.com.henriquearthur.localidadesapi.features.estado.dto.RegiaoEstadoDTO;
import br.com.henriquearthur.localidadesapi.features.estado.ibge.model.IBGEEstado;
import br.com.henriquearthur.localidadesapi.features.municipio.dto.MunicipioDTO;
import br.com.henriquearthur.localidadesapi.features.municipio.ibge.client.IBGEMunicipioClient;
import br.com.henriquearthur.localidadesapi.features.municipio.ibge.model.IBGEMunicipio;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class IBGEMunicipioService {
    private final IBGEMunicipioClient ibgeClient;
    private final ModelMapper modelMapper;

    public IBGEMunicipioService(IBGEMunicipioClient ibgeClient, ModelMapper modelMapper) {
        this.ibgeClient = ibgeClient;
        this.modelMapper = modelMapper;
    }

    public List<MunicipioDTO> getMunicipiosPorEstado(Integer codigoEstado) throws APIRequestException {
        try {
            log.info("Buscando municípios do estado {} no IBGE", codigoEstado);
            List<IBGEMunicipio> municipiosIBGE = ibgeClient.getMunicipiosPorEstado(codigoEstado).getBody();

            if (municipiosIBGE == null) {
                log.error("Erro ao buscar municípios do estado {} no IBGE - resposta vazia", codigoEstado);
                throw new APIRequestException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar municípios no IBGE - resposta vazia");
            }

            return municipiosIBGE.stream().map(this::convertIBGEMunicipioToDTO).toList();
        } catch (FeignException e) {
            log.error("Erro ao buscar estados no IBGE", e);
            throw new APIRequestException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar estados no IBGE - " + e.getMessage());
        }
    }

    public MunicipioDTO getMunicipio(Integer codigo) throws APIRequestException {
        try {
            log.info("Buscando município de código {} no IBGE", codigo);
            IBGEMunicipio municipioIBGE = ibgeClient.getMunicipio(codigo).getBody();

            if (municipioIBGE == null) {
                log.error("Erro ao buscar município {} no IBGE - resposta vazia", codigo);
                throw new APIRequestException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar município " + codigo + " no IBGE - resposta vazia");
            }

            return convertIBGEMunicipioToDTO(municipioIBGE);
        } catch (FeignException e) {
            log.error("Erro ao buscar município no IBGE", e);
            throw new APIRequestException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar município no IBGE - " + e.getMessage());
        }
    }

    public MunicipioDTO convertIBGEMunicipioToDTO(IBGEMunicipio municipio) {
        IBGEEstado estadoIBGE = municipio.getMicrorregiao().getMesorregiao().getUf();

        RegiaoEstadoDTO regiaoEstadoDTO = modelMapper.map(estadoIBGE.getRegiao(), RegiaoEstadoDTO.class);
        EstadoDTO estadoDTO = modelMapper.map(estadoIBGE, EstadoDTO.class);
        estadoDTO.setRegiao(regiaoEstadoDTO);

        MunicipioDTO municipioDTO = new MunicipioDTO();
        municipioDTO.setId(municipio.getId());
        municipioDTO.setNome(municipio.getNome());
        municipioDTO.setEstado(estadoDTO);

        return municipioDTO;
    }
}
