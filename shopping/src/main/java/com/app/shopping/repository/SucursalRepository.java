package com.app.shopping.repository;

import com.app.shopping.model.Sucursal;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface SucursalRepository extends ReactiveCrudRepository<Sucursal, Long> {

    Flux<Sucursal> findByIdFranquicia(Long idFranquicia);
}
