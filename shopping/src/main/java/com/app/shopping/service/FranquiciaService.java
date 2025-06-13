package com.app.shopping.service;

import com.app.shopping.dto.FranquiciaDto;
import com.app.shopping.dto.SucursalDto;
import com.app.shopping.model.Franquicia;
import com.app.shopping.repository.FranquiciaRepository;
import com.app.shopping.repository.ProductoRepository;
import com.app.shopping.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FranquiciaService {

    private final FranquiciaRepository franquiciaRepository;
    private final SucursalRepository sucursalRepository;
    private final ProductoRepository productoRepository;

    @Autowired
    public FranquiciaService(FranquiciaRepository franquiciaRepository, SucursalRepository sucursalRepository, ProductoRepository productoRepository) {
        this.franquiciaRepository = franquiciaRepository;
        this.sucursalRepository = sucursalRepository;
        this.productoRepository = productoRepository;
    }

    public Flux<FranquiciaDto> listarFranquicias() {
        return franquiciaRepository.findAll()
                .flatMap(franquicia ->
                    sucursalRepository.findByIdFranquicia(franquicia.getIdFranquicia())
                            .collectList()
                            .map(sucursales -> {
                                List<SucursalDto> sucursalesDto = sucursales.stream()
                                        .map(sucursal -> new SucursalDto(
                                                sucursal.getIdSucursal(),
                                                sucursal.getNombreSucursal(),
                                                sucursal.getIdFranquicia(),
                                                null))
                                        .collect(Collectors.toList());
                                return new FranquiciaDto(
                                        franquicia.getIdFranquicia(),
                                        franquicia.getNombreFranquicia(),
                                        sucursalesDto
                                );
                            })
                );
    }

    public Mono<FranquiciaDto> agregarFranquicia(FranquiciaDto franquiciaDto){
        Franquicia franquicia = new Franquicia(franquiciaDto.getIdFranquicia(), franquiciaDto.getNombreFranquicia());
        return franquiciaRepository.save(franquicia)
                .map(franquiciaGuardada -> {
                    return new FranquiciaDto(franquiciaGuardada.getIdFranquicia(), franquiciaGuardada.getNombreFranquicia(), null);
                });
    }

    public Mono<FranquiciaDto> actualizarNombreFranquicia(Long idFranquicia, String nombreFranquicia){
        return franquiciaRepository.findById(idFranquicia)
                .flatMap(franquiciaEncontrada -> {
                    franquiciaEncontrada.setNombreFranquicia(nombreFranquicia);
                    return franquiciaRepository.save(franquiciaEncontrada);
                })
                .map(franquiciaActualizada -> {
                    return new FranquiciaDto(franquiciaActualizada.getIdFranquicia(),franquiciaActualizada.getNombreFranquicia(),null
                    );
                });

    }

}
