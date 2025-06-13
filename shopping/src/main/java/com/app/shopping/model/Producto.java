package com.app.shopping.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    @Id
    @Column("id_producto")
    private Long idProducto;

    @Column("nombre_producto")
    private String nombreProducto;

    @Column("cantidad_stock")
    private Long cantidadStock;

    @Column("id_sucursal")
    private Long idSucursal;

}
