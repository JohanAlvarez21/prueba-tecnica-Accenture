package com.app.shopping.service;

import com.app.shopping.dto.ProductoDto;
import com.app.shopping.dto.ProductoMaxStockPorSucursalDto;
import com.app.shopping.model.Producto;
import com.app.shopping.repository.ProductoRepository;
import com.app.shopping.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final SucursalRepository sucursalRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository, SucursalRepository sucursalRepository) {
        this.productoRepository = productoRepository;
        this.sucursalRepository = sucursalRepository;
    }

    public Flux<ProductoDto> listarProductos() {

        return productoRepository.findAll()
                .map(producto -> new ProductoDto(
                        producto.getIdProducto(),
                        producto.getNombreProducto(),
                        producto.getCantidadStock(),
                        producto.getIdSucursal()
                ));
    }

    public Mono<ProductoDto> agregarProducto(ProductoDto productoDto){

        return sucursalRepository.findById(productoDto.getIdSucursal())
                .switchIfEmpty(Mono.error(new RuntimeException("La sucursal no existe")))
                .flatMap(sucursalEncontrada -> {
                    Producto producto = new Producto(
                            productoDto.getIdProducto(),
                            productoDto.getNombreProducto(),
                            productoDto.getCantidadStock(),
                            productoDto.getIdSucursal()
                    );
                    return productoRepository.save(producto);
                })
                .map(productoGuardado -> {
                    return new ProductoDto(productoGuardado.getIdProducto(),productoGuardado.getNombreProducto(),productoGuardado.getCantidadStock(),productoGuardado.getIdSucursal());
                });
    }

    public Mono<ProductoDto> actualizarNombreProducto(Long idProducto, String nombreProducto){
        return productoRepository.findById(idProducto)
                .switchIfEmpty(Mono.error(new RuntimeException("El producto no existe")))
                .flatMap(productoEncontrado -> {
                    productoEncontrado.setNombreProducto(nombreProducto);
                    return productoRepository.save(productoEncontrado);
                })
                .map(productoActualizado -> {
                    return new ProductoDto(productoActualizado.getIdProducto(),productoActualizado.getNombreProducto(),productoActualizado.getCantidadStock(),productoActualizado.getIdSucursal());
                });

    }

    public Mono<ProductoDto> actualizarStockProducto(Long idProducto, Long cantidadStock){
        return productoRepository.findById(idProducto)
                .switchIfEmpty(Mono.error(new RuntimeException("El producto no existe")))
                .flatMap(productoEncontrado -> {
                    productoEncontrado.setCantidadStock(cantidadStock);
                    return productoRepository.save(productoEncontrado);
                })
                .map(productoActualizado -> {
                    return new ProductoDto(productoActualizado.getIdProducto(),productoActualizado.getNombreProducto(),productoActualizado.getCantidadStock(),productoActualizado.getIdSucursal());
                });

    }

    public Mono<Void> eliminarProductoDeSucursal(Long idProducto, Long idSucursal) {
        return productoRepository.findById(idProducto)
                .switchIfEmpty(Mono.error(new RuntimeException("El producto no existe")))
                .flatMap(productoEncontrado -> {
                    if (!productoEncontrado.getIdSucursal().equals(idSucursal)) {
                        return Mono.error(new RuntimeException("El producto no pertenece a la sucursal indicada"));
                    }
                    return productoRepository.deleteById(idProducto);
                });
    }

    public Flux<ProductoMaxStockPorSucursalDto> obtenerProductosConMayorStockPorFranquicia(Long idFranquicia) {
        return sucursalRepository.findByIdFranquicia(idFranquicia)
                .flatMap(sucursal ->
                        productoRepository.findFirstByIdSucursalOrderByCantidadStockDesc(sucursal.getIdSucursal())
                                .map(producto -> new ProductoMaxStockPorSucursalDto(
                                        producto.getIdProducto(),
                                        producto.getNombreProducto(),
                                        producto.getCantidadStock(),
                                        sucursal.getNombreSucursal(),
                                        sucursal.getIdSucursal()

                                ))
                );
    }
}
