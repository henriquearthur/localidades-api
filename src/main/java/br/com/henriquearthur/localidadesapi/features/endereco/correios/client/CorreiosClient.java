package br.com.henriquearthur.localidadesapi.features.endereco.correios.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "correiosClient", url = "${correios.api.url}")
public interface CorreiosClient {
    @PostMapping
    ResponseEntity<String> getEndereco(@RequestBody String body);
}
