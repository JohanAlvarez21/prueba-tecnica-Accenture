package com.app.shopping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SucursalDto {

    private Long idSucursal;

    private String nombreSucursal;

    private Long idFranquicia;

    private List<ProductoDto> productos;

}
