package com.app.shopping.service;

import com.app.shopping.dto.ProductoDto;
import com.app.shopping.dto.SucursalDto;
import com.app.shopping.model.Sucursal;
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
public class SucursalService {

    private final SucursalRepository sucursalRepository;
    private final FranquiciaRepository franquiciaRepository;
    private final ProductoRepository productoRepository;

    @Autowired
    public SucursalService(SucursalRepository sucursalRepository, FranquiciaRepository franquiciaRepository, ProductoRepository productoRepository) {
        this.sucursalRepository = sucursalRepository;
        this.franquiciaRepository = franquiciaRepository;
        this.productoRepository = productoRepository;
    }

    public Flux<SucursalDto> listarSucursales() {
        return sucursalRepository.findAll()
                .flatMap(sucursal ->
                        productoRepository.findByIdSucursal(sucursal.getIdSucursal())
                                .collectList()
                                .map(productos -> {
                                    List<ProductoDto> productosDto = productos.stream()
                                            .map(producto -> new ProductoDto(
                                                    producto.getIdProducto(),
                                                    producto.getNombreProducto(),
                                                    producto.getCantidadStock(),
                                                    producto.getIdSucursal()))
                                            .collect(Collectors.toList());
                                    return new SucursalDto(
                                            sucursal.getIdSucursal(),
                                            sucursal.getNombreSucursal(),
                                            sucursal.getIdFranquicia(),
                                            productosDto
                                    );
                                })
                );
    }

    public Mono<SucursalDto> agregarSucursal(SucursalDto sucursalDto){

        return franquiciaRepository.findById(sucursalDto.getIdFranquicia())
                .flatMap(franquiciaEncontrada -> {
                    Sucursal sucursal = new Sucursal(
                        sucursalDto.getIdSucursal(),
                        sucursalDto.getNombreSucursal(),
                        sucursalDto.getIdFranquicia()
                    );
                    return sucursalRepository.save(sucursal);
                })
                .map(sucursalGuardada -> {
                    return new SucursalDto(sucursalGuardada.getIdSucursal(),sucursalGuardada.getNombreSucursal(),sucursalGuardada.getIdFranquicia(),null);
                });
    }

    public Mono<SucursalDto> actualizarNombreSucursal(Long idSucursal, String nombreSucursal){
        return sucursalRepository.findById(idSucursal)
                .flatMap(sucursalEncontrada -> {
                    sucursalEncontrada.setNombreSucursal(nombreSucursal);
                    return sucursalRepository.save(sucursalEncontrada);
                })
                .map(sucursalActualizada -> {
                    return new SucursalDto(sucursalActualizada.getIdSucursal(),sucursalActualizada.getNombreSucursal(),sucursalActualizada.getIdFranquicia(),null);
                });

    }
}
