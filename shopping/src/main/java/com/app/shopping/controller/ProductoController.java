package com.app.shopping.controller;

import com.app.shopping.dto.ProductoDto;
import com.app.shopping.dto.ProductoMaxStockPorSucursalDto;
import com.app.shopping.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Map;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/listarProductos")
    public Mono<ResponseEntity<Flux<ProductoDto>>> listarProductos() {
        Flux<ProductoDto> productos = productoService.listarProductos();
        return Mono.just(ResponseEntity.ok(productos));
    }

    @PostMapping("/agregarProducto")
    public Mono<ResponseEntity<ProductoDto>> agregarProducto(@RequestBody ProductoDto productoDto) {
        return productoService.agregarProducto(productoDto)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/actualizarNombreProducto/{idProducto}")
    public Mono<ResponseEntity<ProductoDto>> actualizarNombreProducto(@PathVariable Long idProducto,@RequestBody ProductoDto productoDto) {

        return productoService.actualizarNombreProducto(idProducto, productoDto.getNombreProducto())
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @PutMapping("/actualizarStockProducto/{idProducto}")
    public Mono<ResponseEntity<ProductoDto>> actualizarStockProducto(@PathVariable Long idProducto,@RequestBody ProductoDto productoDto) {

        return productoService.actualizarStockProducto(idProducto, productoDto.getCantidadStock())
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/eliminarProductoDeSucursal/{idProducto}/sucursal/{idSucursal}")
    public Mono<ResponseEntity<Map<String, String>>> eliminarProductoDeSucursal(@PathVariable Long idProducto, @PathVariable Long idSucursal) {
        return productoService.eliminarProductoDeSucursal(idProducto, idSucursal)
                .thenReturn(ResponseEntity.ok(Map.of("mensaje", "Producto eliminado")))
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().build()));
    }

    @GetMapping("/obtenerProductosMaxStockPorFranquicia/{idFranquicia}")
    public Mono<ResponseEntity<Flux<ProductoMaxStockPorSucursalDto>>> obtenerProductosMaxStockPorFranquicia(@PathVariable Long idFranquicia) {
        Flux<ProductoMaxStockPorSucursalDto> resultado = productoService.obtenerProductosConMayorStockPorFranquicia(idFranquicia);
        return Mono.just(ResponseEntity.ok(resultado));
    }

}
