package com.app.shopping.repository;

import com.app.shopping.model.Producto;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductoRepository extends ReactiveCrudRepository<Producto, Long> {

    Mono<Producto> findFirstByIdSucursalOrderByCantidadStockDesc(Long idSucursal);
    Flux<Producto> findByIdSucursal(Long idSucursal);
}
