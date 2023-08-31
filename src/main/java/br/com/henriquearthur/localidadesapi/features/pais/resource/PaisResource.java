package br.com.henriquearthur.localidadesapi.features.pais.resource;

import br.com.henriquearthur.localidadesapi.exception.APIRequestException;
import br.com.henriquearthur.localidadesapi.features.pais.dto.PaisDTO;
import br.com.henriquearthur.localidadesapi.features.pais.service.PaisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pais")
public class PaisResource {
    private final PaisService paisService;

    public PaisResource(PaisService paisService) {
        this.paisService = paisService;
    }


    @GetMapping
    public ResponseEntity<List<PaisDTO>> getPaises() throws APIRequestException {
        return ResponseEntity.ok(paisService.getPaises());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<PaisDTO> getPais(@PathVariable("codigo") String codigo) throws APIRequestException {
        return ResponseEntity.ok(paisService.getPais(codigo));
    }
}
