package com.app.shopping.controller;

import com.app.shopping.dto.SucursalDto;
import com.app.shopping.service.SucursalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/sucursales")
public class SucursalController {

    private final SucursalService sucursalService;

    public SucursalController(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    @GetMapping("/listarSucursales")
    public Mono<ResponseEntity<Flux<SucursalDto>>> listarSucursales() {
        Flux<SucursalDto> sucursales = sucursalService.listarSucursales();
        return Mono.just(ResponseEntity.ok(sucursales));
    }

    @PostMapping("/agregarSucursal")
    public Mono<ResponseEntity<SucursalDto>> agregarSucursal(@RequestBody SucursalDto sucursalDto) {
        return sucursalService.agregarSucursal(sucursalDto)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @PutMapping("/actualizarNombreSucursal/{idSucursal}")
    public Mono<ResponseEntity<SucursalDto>> actualizarNombreSucursal(@PathVariable Long idSucursal,@RequestBody SucursalDto sucursalDto) {
        return sucursalService.actualizarNombreSucursal(idSucursal, sucursalDto.getNombreSucursal())
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

}
