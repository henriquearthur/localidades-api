package br.com.henriquearthur.localidadesapi.features.endereco.resource;

import br.com.henriquearthur.localidadesapi.exception.APIRequestException;
import br.com.henriquearthur.localidadesapi.features.endereco.dto.EnderecoDTO;
import br.com.henriquearthur.localidadesapi.features.endereco.service.EnderecoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/endereco")
public class EnderecoResource {
    private final EnderecoService enderecoService;

    public EnderecoResource(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<EnderecoDTO> getEndereco(@PathVariable("cep") String cep) throws APIRequestException {
        return ResponseEntity.ok(enderecoService.getEndereco(cep));
    }

}
