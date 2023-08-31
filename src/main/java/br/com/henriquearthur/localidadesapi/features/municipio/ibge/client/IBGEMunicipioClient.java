package br.com.henriquearthur.localidadesapi.features.municipio.ibge.client;


import br.com.henriquearthur.localidadesapi.features.municipio.ibge.model.IBGEMunicipio;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ibgeMunicipioClient", url = "${ibge.api.url}/localidades")
public interface IBGEMunicipioClient {
    @GetMapping("/estados/{codigoEstado}/municipios")
    ResponseEntity<List<IBGEMunicipio>> getMunicipiosPorEstado(@RequestParam Integer codigoEstado);

    @GetMapping("/municipios/{codigoMunicipio}")
    ResponseEntity<IBGEMunicipio> getMunicipio(@RequestParam Integer codigoMunicipio);
}
