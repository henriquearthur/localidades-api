package br.com.henriquearthur.localidadesapi.features.estado.resource;

import br.com.henriquearthur.localidadesapi.features.estado.dto.EstadoDTO;
import br.com.henriquearthur.localidadesapi.features.estado.service.EstadoService;
import br.com.henriquearthur.localidadesapi.exception.APIRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estado")
public class EstadoResource {
    private final EstadoService estadoService;

    public EstadoResource(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @GetMapping
    public ResponseEntity<List<EstadoDTO>> getEstados() {
        return ResponseEntity.ok(estadoService.getEstados());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<EstadoDTO> getPais(@PathVariable("codigo") Integer codigo) throws APIRequestException {
        return ResponseEntity.ok(estadoService.getEstado(codigo));
    }
}
