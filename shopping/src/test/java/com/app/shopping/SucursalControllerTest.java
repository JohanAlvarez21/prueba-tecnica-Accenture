package com.app.shopping;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.app.shopping.controller.SucursalController;
import com.app.shopping.dto.SucursalDto;
import com.app.shopping.service.SucursalService;
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
public class SucursalControllerTest {

    @Mock
    private SucursalService sucursalService;

    @InjectMocks
    private SucursalController sucursalController;

    // Test para listarSucursales (Exito)
    @Test
    void testListarSucursales() {
        // GIVEN
        SucursalDto sucursal1 = new SucursalDto(1L, "Mercadolibre Colombia", 10L, null);
        SucursalDto sucursal2 = new SucursalDto(2L, "Mercadolibre Mexico", 20L, null);
        Flux<SucursalDto> sucursales = Flux.fromIterable(List.of(sucursal1, sucursal2));
        when(sucursalService.listarSucursales()).thenReturn(sucursales);

        // WHEN
        Mono<ResponseEntity<Flux<SucursalDto>>> resultadoMono = sucursalController.listarSucursales();
        ResponseEntity<Flux<SucursalDto>> respuesta = resultadoMono.block();

        // THEN
        assertNotNull(respuesta);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertNotNull(respuesta.getBody());
        verify(sucursalService, times(1)).listarSucursales();
    }

    // Test para agregarSucursal (Exito)
    @Test
    void testAgregarSucursal_Exito() {
        // GIVEN
        SucursalDto sucursalDto = new SucursalDto(1L, "Mercadolibre Colombia", 10L, null);
        when(sucursalService.agregarSucursal(sucursalDto)).thenReturn(Mono.just(sucursalDto));

        // WHEN
        Mono<ResponseEntity<SucursalDto>> resultadoMono = sucursalController.agregarSucursal(sucursalDto);
        ResponseEntity<SucursalDto> respuesta = resultadoMono.block();

        // THEN
        assertNotNull(respuesta);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(sucursalDto, respuesta.getBody());
        verify(sucursalService, times(1)).agregarSucursal(sucursalDto);
    }

    // Test para agregarSucursal (caso no encontrado)
    @Test
    void testAgregarSucursal_NoEncontrado() {
        // GIVEN
        SucursalDto sucursalDto = new SucursalDto(1L, "Mercadolibre Colombia", 10L, null);
        when(sucursalService.agregarSucursal(sucursalDto)).thenReturn(Mono.empty());

        // WHEN
        Mono<ResponseEntity<SucursalDto>> resultadoMono = sucursalController.agregarSucursal(sucursalDto);
        ResponseEntity<SucursalDto> respuesta = resultadoMono.block();

        // THEN
        assertNotNull(respuesta);
        assertEquals(404, respuesta.getStatusCodeValue());
        assertNull(respuesta.getBody());
        verify(sucursalService, times(1)).agregarSucursal(sucursalDto);
    }

    // Test para actualizarNombreSucursal (Exito)
    @Test
    void testActualizarNombreSucursal_Exito() {
        // GIVEN
        Long idSucursal = 1L;
        String nuevoNombre = "Sucursal Actualizada";
        Long idFranquicia = 10L;

        SucursalDto sucursalActualizada = new SucursalDto(idSucursal, nuevoNombre, idFranquicia, null);
        when(sucursalService.actualizarNombreSucursal(idSucursal, nuevoNombre)).thenReturn(Mono.just(sucursalActualizada));

        SucursalDto sucursal = new SucursalDto(null, nuevoNombre, idFranquicia, null);

        // WHEN
        Mono<ResponseEntity<SucursalDto>> resultadoMono = sucursalController.actualizarNombreSucursal(idSucursal, sucursal);
        ResponseEntity<SucursalDto> respuesta = resultadoMono.block();

        // THEN
        assertNotNull(respuesta);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(sucursalActualizada, respuesta.getBody());
        verify(sucursalService, times(1)).actualizarNombreSucursal(idSucursal, nuevoNombre);
    }

    // Test para actualizarNombreSucursal (caso no encontrado)
    @Test
    void testActualizarNombreSucursal_NoEncontrado() {
        // GIVEN
        Long idSucursal = 1L;
        String nuevoNombre = "Sucursal Actualizada";
        Long idFranquicia = 10L;

        when(sucursalService.actualizarNombreSucursal(idSucursal, nuevoNombre)).thenReturn(Mono.empty());
        SucursalDto sucursal = new SucursalDto(null, nuevoNombre, idFranquicia, null);

        // WHEN
        Mono<ResponseEntity<SucursalDto>> resultadoMono = sucursalController.actualizarNombreSucursal(idSucursal, sucursal);
        ResponseEntity<SucursalDto> respuesta = resultadoMono.block();

        // THEN
        assertNotNull(respuesta);
        assertEquals(404, respuesta.getStatusCodeValue());
        assertNull(respuesta.getBody());
        verify(sucursalService, times(1)).actualizarNombreSucursal(idSucursal, nuevoNombre);
    }
}
