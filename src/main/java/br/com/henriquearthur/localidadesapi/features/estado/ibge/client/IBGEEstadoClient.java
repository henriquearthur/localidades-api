package br.com.henriquearthur.localidadesapi.features.estado.ibge.client;

import br.com.henriquearthur.localidadesapi.features.estado.ibge.model.IBGEEstado;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ibgeEstadoClient", url = "${ibge.api.url}/localidades/estados")
public interface IBGEEstadoClient {
    @GetMapping
    ResponseEntity<List<IBGEEstado>> getEstados(@RequestParam(required = false) String orderBy);

    @GetMapping("/{id}")
    ResponseEntity<IBGEEstado> getEstado(@PathVariable("id") Integer id);
}
