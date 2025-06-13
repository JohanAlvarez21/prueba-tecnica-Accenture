# pruebaTecnica-Accenture
# Proyecto de shopping

Este proyecto es una aplicación desarrollada en **Spring Boot** que permite gestionar el sistema de franquicias, implementando arquitectura en capas, persistencia de datos y servicios REST.
Instalación Ide IntelliJ IDEA

## Requisitos previos

### 1- Instalación del IDE - IntelliJ IDEA
  
  Se recomienda utilizar **IntelliJ IDEA** como entorno de desarrollo.

  Descarga: [IntelliJ IDEA Download](https://www.jetbrains.com/es-es/idea/download/?section=windows)

### 2- Instalación de Java 17
    
  El proyecto está desarrollado con **Java 17**, por lo que es necesario tenerlo instalado. --elegir versión según el sistema operativo

  Descarga: [Java 17 JDK](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
  
### 3- Instalacion de Docker Desktop
  
  La base de datos y la aplicación están dockerizadas por lo que tenemos que instalar Docker Desktop

  Descarga [DockerDesktop](https://www.docker.com/products/docker-desktop/)
  
## Estructura del proyecto
  Para este paso dentro de la ruta shopping/src/main/java/com.app.shopping se crearon los siguientes paquetes:
    -model // Entidades
    -dto // Objetos de transferencia de datos
    -repository // Repositorio de datos (DAO)
    -service // Lógica de negocio
    -controller // Controladores REST

## Configuración de la base de datos
  En el archivo application.properties (o application.yml) se debe configurar el acceso a la base de datos, la conexión se realizará con MySQL:
  
    - Base de datos de manera local 
    
  ![image](https://github.com/user-attachments/assets/82bfb18f-9580-45bd-84d0-e9e39bf23daf)

## Ejecutar proyecto

  Abrimos una terminal dentro de la ruta del proyecto y ejecutamos:
  
  docker-compose build
  
![image](https://github.com/user-attachments/assets/dfa82d89-3528-4808-93ff-8d7df89fac05)

  docker-compose up
  
![image](https://github.com/user-attachments/assets/ca25064b-2716-48e5-bb31-ae2fa2e3fd4f)


  


## Ejemplos uso de la API:

### Endpoint para el API de franquicias

  -Obtener todas las franquicias: Este endpoint devolverá una lista con todas las franquicias
    -http://localhost:8080/franquicias/listarFranquicias
    
    Resultado
  ![image](https://github.com/user-attachments/assets/4a00943e-8319-406f-80d6-5979cda66915)

  
  -Crear una franquicia: Este endpoint recibirá un json body con el nombre de la franquicia
    -http://localhost:8080/franquicias/agregarFranquicia

    Body
      {
        "nombreFranquicia": "Fallabella SAS"
      }

    Resultado
  ![image](https://github.com/user-attachments/assets/d857233f-7c4a-4396-a8b2-0c4221e2c1ec)


  -Actualizar nombre a una franquicia: Este endpoint recibirá cómo parámetro el id de la franquicia y un json body con el nuevo nombre
    -http://localhost:8080/franquicias/actualizarNombreFranquicia/{idFranquicia}

    Body
      {
        "nombreFranquicia": "Fallabella"
      }

    Resultado
  ![image](https://github.com/user-attachments/assets/d0f8bad6-33c8-497f-94e7-4a5a2299361c)


### Endpoint para el API de sucursales

  -Obtener todas las sucursales: Este endpoint devolverá una lista con todas las sucursales
    -http://localhost:8080/sucursales/listarSucursales
    
    Resultado
  ![image](https://github.com/user-attachments/assets/90c4e900-f262-44dc-82d2-3e4e075ecb89)

  
  -Crear una sucursal: Este endpoint recibirá un json body con el nombre de la sucursal y el id de la franquicia a la que se quiere relacionar
    -http://localhost:8080/sucursales/agregarSucursal

    Body
      {
        "nombreSucursal":"Exito Bogota",
        "idFranquicia": 1
      }

    Resultado
  ![image](https://github.com/user-attachments/assets/24619f30-0384-4387-afe3-071c145032b0)


  -Actualizar nombre a una sucursal: Este endpoint recibirá cómo parámetro el id de la sucursal y un json body con el nuevo nombre
    -http://localhost:8080/sucursales/actualizarNombreSucursal/{idSucursal}

    Body
      {
        "nombreSucursal":"Exito Suba"
      }

    Resultado
  ![image](https://github.com/user-attachments/assets/6e7f9b86-dbb6-45d9-bd1d-3deaabbfa29f)



### Endpoint para el API de productos

  -Obtener todos los productos: Este endpoint devolverá una lista con todos los productos
    -http://localhost:8080/productos/listarProdcutos
    
    Resultado
  ![image](https://github.com/user-attachments/assets/2792a23d-9116-490c-b045-7addcfac8bd9)

  
  -Crear un producto: Este endpoint recibirá un json body con el nombre del producto y el id de la sucursal a la que se quiere relacionar
    -http://localhost:8080/productos/agregarProducto

    Body
      {
        "nombreProducto":"Parlantes",
        "cantidadStock":100,
        "idSucursal": 1
      }
      
    Resultado
  ![image](https://github.com/user-attachments/assets/24c24c95-28ea-42a1-9efc-6eaa36e0e9b6)


  -Actualizar nombre a un producto: Este endpoint recibirá cómo parámetro el id del producto y un json body con el nuevo nombre
    -http://localhost:8080/productos/actualizarNombreProducto/{idProducto}

    Body
      {
        "nombreProducto":"Parlantes Bluetooth"
      }

    Resultado
  ![image](https://github.com/user-attachments/assets/570628f0-0d01-48e5-b706-9e1895d02ea0)


  -Actualizar cantidad de stock a un producto: Este endpoint recibirá cómo parámetro el id del producto y un json body con la nueva cantidad
      -http://localhost:8080/productos/actualizarStockProducto/{idProducto}
  
      Body
        {
          "cantidadStock": 200
        }
  
      Resultado
  ![image](https://github.com/user-attachments/assets/a2e69501-0bf6-4f23-ba2f-dd85cb9c5d93)

  
  -Eliminar un producto de una sucursal: Este endpoint recibirá cómo parámetros el id del producto y el id de la sucursal
      -http://localhost:8080/productos/eliminarProductoDeSucursal/{idProducto}/sucursal/{idSucursal}
    
      Resultado
  ![image](https://github.com/user-attachments/assets/2896a562-f007-434c-a312-bb8a395d32f4)

  
  -Obtener producto con más stock por franquicia: Este endpoint recibirá cómo parámetro el id de la franquicia
      -http://localhost:8080/productos/obtenerProductosMaxStockPorFranquicia/{idFranquicia}
  
      Resultado
  ![image](https://github.com/user-attachments/assets/464efc82-3613-40f5-85f9-da1fe0090b5b)

