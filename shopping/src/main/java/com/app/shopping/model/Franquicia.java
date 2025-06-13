package com.app.shopping.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("franquicias")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Franquicia {

    @Id
    @Column("id_franquicia")
    private Long idFranquicia;

    @Column("nombre_franquicia")
    private String nombreFranquicia;

}
