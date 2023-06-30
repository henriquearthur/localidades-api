package br.com.henriquearthur.localidadesapi.pais.ibge.client;

import br.com.henriquearthur.localidadesapi.pais.ibge.model.IBGEPais;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ibgePaisClient", url = "${ibge.api.url}/localidades/paises")
public interface IBGEPaisClient {
    @GetMapping
    ResponseEntity<List<IBGEPais>> getPaises(@RequestParam(required = false) String orderBy);

    @GetMapping("/{id}")
    ResponseEntity<List<IBGEPais>> getPais(@PathVariable("id") String id);
}
