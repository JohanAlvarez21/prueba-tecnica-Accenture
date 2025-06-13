package com.app.shopping;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.app.shopping.controller.ProductoController;
import com.app.shopping.dto.ProductoDto;
import com.app.shopping.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class ProductoControllerTest {

    @Mock
    private ProductoService productoService;

    @InjectMocks
    private ProductoController productoController;

    // 1 - Test listarProductos
    @Test
    void testListarProductos() {
        // GIVEN
        ProductoDto producto1 = new ProductoDto(1L, "Televisor", 10L, 100L);
        ProductoDto producto2 = new ProductoDto(2L, "Celular", 20L, 200L);
        Flux<ProductoDto> productos = Flux.fromIterable(List.of(producto1, producto2));
        when(productoService.listarProductos()).thenReturn(productos);

        // WHEN
        Mono<ResponseEntity<Flux<ProductoDto>>> resultadoMono = productoController.listarProductos();
        ResponseEntity<Flux<ProductoDto>> respuesta = resultadoMono.block();

        // THEN
        assertNotNull(respuesta);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertNotNull(respuesta.getBody());
        verify(productoService, times(1)).listarProductos();
    }

    // 2 - Test agregarProducto (Exito)
    @Test
    void testAgregarProducto_Exito() {
        // GIVEN
        ProductoDto productoDto = new ProductoDto(1L, "Televisor", 10L, 100L);
        when(productoService.agregarProducto(productoDto)).thenReturn(Mono.just(productoDto));

        // WHEN
        Mono<ResponseEntity<ProductoDto>> resultadoMono = productoController.agregarProducto(productoDto);
        ResponseEntity<ProductoDto> respuesta = resultadoMono.block();

        // THEN
        assertNotNull(respuesta);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(productoDto, respuesta.getBody());
        verify(productoService, times(1)).agregarProducto(productoDto);
    }

    // 3 - Test actualizarNombreProducto (Exito)
    @Test
    void testActualizarNombreProducto_Exito() {
        // GIVEN
        Long idProducto = 1L;
        String nuevoNombre = "Producto Actualizado";
        ProductoDto productoActualizado = new ProductoDto(idProducto, nuevoNombre, 10L, 100L);
        when(productoService.actualizarNombreProducto(idProducto, nuevoNombre)).thenReturn(Mono.just(productoActualizado));

        ProductoDto producto = new ProductoDto(null, nuevoNombre, 10L, 100L);

        // WHEN
        Mono<ResponseEntity<ProductoDto>> resultadoMono = productoController.actualizarNombreProducto(idProducto, producto);
        ResponseEntity<ProductoDto> respuesta = resultadoMono.block();

        // THEN
        assertNotNull(respuesta);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(productoActualizado, respuesta.getBody());
        verify(productoService, times(1)).actualizarNombreProducto(idProducto, nuevoNombre);
    }

    // 4 - Test actualizarPrecioProducto (Exito)
    @Test
    void testActualizarStockProducto_Exito() {
        // GIVEN
        Long idProducto = 1L;
        Long nuevoStock = 150L;

        ProductoDto productoActualizado = new ProductoDto(idProducto, "Producto A", nuevoStock, 10L);
        when(productoService.actualizarStockProducto(idProducto, nuevoStock)).thenReturn(Mono.just(productoActualizado));

        ProductoDto producto = new ProductoDto(null, null, nuevoStock, null);

        // WHEN
        Mono<ResponseEntity<ProductoDto>> resultadoMono = productoController.actualizarStockProducto(idProducto, producto);
        ResponseEntity<ProductoDto> respuesta = resultadoMono.block();

        // THEN
        assertNotNull(respuesta);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(productoActualizado, respuesta.getBody());
        verify(productoService, times(1)).actualizarStockProducto(idProducto, nuevoStock);
    }

    // 5 - Test actualizarPrecioProducto (No encontrado)
    @Test
    void testActualizarStockProducto_NoEncontrado() {
        // GIVEN
        Long idProducto = 1L;
        Long nuevoStock = 150L;

        when(productoService.actualizarStockProducto(idProducto, nuevoStock)).thenReturn(Mono.empty());

        ProductoDto producto = new ProductoDto(null, null, nuevoStock, null);

        // WHEN
        Mono<ResponseEntity<ProductoDto>> resultadoMono = productoController.actualizarStockProducto(idProducto, producto);
        ResponseEntity<ProductoDto> respuesta = resultadoMono.block();

        // THEN
        assertNotNull(respuesta);
        assertEquals(404, respuesta.getStatusCodeValue());
        assertNull(respuesta.getBody());
        verify(productoService, times(1)).actualizarStockProducto(idProducto, nuevoStock);
    }


    // 6 - Test eliminarProducto (Exito)
    @Test
    void testEliminarProductoDeSucursal_Exito() {
        // GIVEN
        Long idProducto = 1L;
        Long idSucursal = 100L;

        when(productoService.eliminarProductoDeSucursal(idProducto, idSucursal)).thenReturn(Mono.empty());

        // WHEN
        Mono<ResponseEntity<Map<String, String>>> resultadoMono = productoController.eliminarProductoDeSucursal(idProducto, idSucursal);
        ResponseEntity<Map<String, String>> respuesta = resultadoMono.block();

        // THEN
        assertNotNull(respuesta);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals("Producto eliminado", respuesta.getBody().get("mensaje"));
        verify(productoService, times(1)).eliminarProductoDeSucursal(idProducto, idSucursal);
    }

    // 7 - Test eliminarProducto (Error)
    @Test
    void testEliminarProductoDeSucursal_Error() {
        // GIVEN
        Long idProducto = 1L;
        Long idSucursal = 100L;

        when(productoService.eliminarProductoDeSucursal(idProducto, idSucursal)).thenReturn(Mono.error(new RuntimeException("Error simulado")));

        // WHEN
        Mono<ResponseEntity<Map<String, String>>> resultadoMono = productoController.eliminarProductoDeSucursal(idProducto, idSucursal);
        ResponseEntity<Map<String, String>> respuesta = resultadoMono.block();

        // THEN
        assertNotNull(respuesta);
        assertEquals(400, respuesta.getStatusCodeValue());
        assertNull(respuesta.getBody());
        verify(productoService, times(1)).eliminarProductoDeSucursal(idProducto, idSucursal);
    }


}

