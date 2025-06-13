package com.app.shopping.dto;

import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FranquiciaDto {

    private Long idFranquicia;

    private String nombreFranquicia;

    private List<SucursalDto> sucursales;

}
