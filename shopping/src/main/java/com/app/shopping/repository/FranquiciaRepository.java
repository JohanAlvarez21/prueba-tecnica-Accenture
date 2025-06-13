package com.app.shopping.repository;

import com.app.shopping.model.Franquicia;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranquiciaRepository extends ReactiveCrudRepository<Franquicia, Long> {

}
