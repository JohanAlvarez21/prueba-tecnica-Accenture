package com.app.shopping;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.app.shopping.controller.FranquiciaController;
import com.app.shopping.dto.FranquiciaDto;
import com.app.shopping.service.FranquiciaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class FranquiciaControllerTest {

    @Mock
    private FranquiciaService franquiciaService;

    @InjectMocks
    private FranquiciaController franquiciaController;

    // Test para listarFranquicias (Exito)
    @Test
    void testListarFranquicias() {
        // Given
        FranquiciaDto franquicia1 = new FranquiciaDto(1L, "Mercadolibre", null);
        FranquiciaDto franquicia2 = new FranquiciaDto(2L, "Amazon", null);

        Flux<FranquiciaDto> franquicias = Flux.fromIterable(List.of(franquicia1, franquicia2));

        // When
        when(franquiciaService.listarFranquicias()).thenReturn(franquicias);

        // Then
        Mono<ResponseEntity<Flux<FranquiciaDto>>> resultadoMono = franquiciaController.listarFranquicias();


        ResponseEntity<Flux<FranquiciaDto>> respuesta = resultadoMono.block();
        assertNotNull(respuesta);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertNotNull(respuesta.getBody());
    }

    // Test para crearFranquicia (Exito)
    @Test
    void testCrearFranquicia() {
        // Given
        FranquiciaDto franquiciaDto = new FranquiciaDto(1L, "Amazon", null);

        // When
        when(franquiciaService.agregarFranquicia(franquiciaDto)).thenReturn(Mono.just(franquiciaDto));

        // Then
        Mono<ResponseEntity<FranquiciaDto>> resultadoMono = franquiciaController.crearFranquicia(franquiciaDto);


        ResponseEntity<FranquiciaDto> respuesta = resultadoMono.block();
        assertNotNull(respuesta);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(franquiciaDto, respuesta.getBody());
    }

    // Test para actualizarNombreFranquicia (Exito)
    @Test
    void testActualizarNombreFranquicia_Exito() {
        // Given
        Long idFranquicia = 1L;
        String nuevoNombre = "Franquicia Actualizada";
        FranquiciaDto franquiciaActualizada = new FranquiciaDto(idFranquicia, nuevoNombre, null);

        // when
        when(franquiciaService.actualizarNombreFranquicia(idFranquicia, nuevoNombre))
                .thenReturn(Mono.just(franquiciaActualizada));

        FranquiciaDto franquicia = new FranquiciaDto(null, nuevoNombre, null);

        // then
        Mono<ResponseEntity<FranquiciaDto>> resultadoMono = franquiciaController.actualizarNombreFranquicia(idFranquicia, franquicia);


        ResponseEntity<FranquiciaDto> respuesta = resultadoMono.block();
        assertNotNull(respuesta);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(franquiciaActualizada, respuesta.getBody());
    }

    // Test para actualizarNombreFranquicia (caso no encontrado)
    @Test
    void testActualizarNombreFranquicia_NoEncontrado() {
        // Arrange
        Long idFranquicia = 1L;
        String nuevoNombre = "Franquicia Actualizada";

        when(franquiciaService.actualizarNombreFranquicia(idFranquicia, nuevoNombre))
                .thenReturn(Mono.empty());

        FranquiciaDto franquicia = new FranquiciaDto(null, nuevoNombre, null);

        // Act
        Mono<ResponseEntity<FranquiciaDto>> resultadoMono = franquiciaController.actualizarNombreFranquicia(idFranquicia, franquicia);

        // Assert
        ResponseEntity<FranquiciaDto> respuesta = resultadoMono.block();
        assertNotNull(respuesta);
        assertEquals(404, respuesta.getStatusCodeValue());
        assertNull(respuesta.getBody());
    }
}

