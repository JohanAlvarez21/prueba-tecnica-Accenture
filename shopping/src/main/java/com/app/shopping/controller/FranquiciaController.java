package com.app.shopping.controller;

import com.app.shopping.dto.FranquiciaDto;
import com.app.shopping.service.FranquiciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/franquicias")
public class FranquiciaController {

    private final FranquiciaService franquiciaService;

    public FranquiciaController(FranquiciaService franquiciaService) {
        this.franquiciaService = franquiciaService;
    }

    @GetMapping("/listarFranquicias")
    public Mono<ResponseEntity<Flux<FranquiciaDto>>> listarFranquicias() {
        Flux<FranquiciaDto> franquicias = franquiciaService.listarFranquicias();
        return Mono.just(ResponseEntity.ok(franquicias));
    }

    @PostMapping("/agregarFranquicia")
    public Mono<ResponseEntity<FranquiciaDto>> crearFranquicia(@RequestBody FranquiciaDto franquiciaDto) {
        return franquiciaService.agregarFranquicia(franquiciaDto)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/actualizarNombreFranquicia/{idFranquicia}")
    public Mono<ResponseEntity<FranquiciaDto>> actualizarNombreFranquicia(@PathVariable Long idFranquicia, @RequestBody FranquiciaDto franquiciaDto) {
        return franquiciaService.actualizarNombreFranquicia(idFranquicia, franquiciaDto.getNombreFranquicia())
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }
}
