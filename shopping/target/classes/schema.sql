CREATE TABLE IF NOT EXISTS franquicias (
    id_franquicia BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_franquicia VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS sucursales (
    id_sucursal BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_sucursal VARCHAR(255) NOT NULL,
    id_franquicia BIGINT NOT NULL,
    FOREIGN KEY (id_franquicia) REFERENCES franquicias(id_franquicia)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS productos (
    id_producto BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_producto VARCHAR(255) NOT NULL,
    cantidad_stock BIGINT NOT NULL,
    id_sucursal BIGINT NOT NULL,
    FOREIGN KEY (id_sucursal) REFERENCES sucursales(id_sucursal)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

INSERT INTO franquicias (nombre_franquicia)
VALUES ('Exito SAS');
