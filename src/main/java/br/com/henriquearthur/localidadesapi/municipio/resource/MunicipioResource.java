package br.com.henriquearthur.localidadesapi.municipio.resource;

import br.com.henriquearthur.localidadesapi.exception.APIRequestException;
import br.com.henriquearthur.localidadesapi.municipio.dto.MunicipioDTO;
import br.com.henriquearthur.localidadesapi.municipio.service.MunicipioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/municipio")
public class MunicipioResource {
    private final MunicipioService municipioService;

    public MunicipioResource(MunicipioService municipioService) {
        this.municipioService = municipioService;
    }

    @GetMapping
    public ResponseEntity<List<MunicipioDTO>> getMunicipiosPorEstado(@RequestParam Integer codigoEstado) {
        return ResponseEntity.ok(municipioService.getMunicipiosPorEstado(codigoEstado));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<MunicipioDTO> getMunicipio(@PathVariable("codigo") Integer codigo) throws APIRequestException {
        return ResponseEntity.ok(municipioService.getMunicipio(codigo));
    }
}
