package com.app.shopping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoMaxStockPorSucursalDto {
    private Long idSucursal;
    private String nombreSucursal;
    private Long idProducto;
    private String nombreProducto;
    private Long cantidadStock;

}
