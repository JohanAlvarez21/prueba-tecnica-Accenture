package com.app.shopping.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("sucursales")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sucursal {

    @Id
    @Column("id_sucursal")
    private Long idSucursal;

    @Column("nombre_sucursal")
    private String nombreSucursal;

    @Column("id_franquicia")
    private Long idFranquicia;

}
