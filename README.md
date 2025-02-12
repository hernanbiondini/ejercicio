Esta aplicación es un ejemplo de implementación de una API REST en Java utilizando Spring Boot, siguiendo el patrón de arquitectura hexagonal (Ports & Adapters). La aplicación utiliza H2 como base de datos en memoria y está configurada para funcionar con:

- **Java:** Versión 21.
- **Spring Boot:** Versión 3.4.2.
- **Maven**
- **Lombok**

La aplicación expone los siguientes endpoints:

- **GET** `/empresas/transferencias-ultimo-mes`  
  Recupera la lista de empresas que realizaron al menos una transferencia en el último mes.

- **GET** `/empresas/adhesion-ultimo-mes`  
  Recupera la lista de empresas que se adhirieron (registraron) en el último mes.

- **POST** `/empresas/adhesion`  
  Permite registrar una nueva empresa.  
  **Ejemplo de cuerpo JSON:**
  ```json
  {
    "cuit": "20-12345678-9",
    "razonSocial": "Mi Empresa"
  }


La configuración se define en el archivo `src/main/resources/application.properties`:

```properties
# Configuración de la base de datos H2 en memoria
spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Mostrar SQL en consola y configuración de DDL
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create
```

## Acceso a la Base de Datos

La base de datos se creará en memoria con el nombre `testdb`.  
La consola H2 estará disponible en [http://localhost:8080/h2-console](http://localhost:8080/h2-console).

> ⚠ **IMPORTANTE:** Si en los logs aparece un URL diferente (por ejemplo,  
> `jdbc:h2:mem:5c8e2d55-1f83-40a9-8905-4d8495af32b9`), utiliza ese URL en la consola.  
> Esto puede ocurrir si existe alguna sobreescritura o conflicto en la configuración.


# Ejecución de la Aplicación

## Compilar y Ejecutar

Desde la raíz del proyecto, ejecuta el siguiente comando:

```bash
mvn spring-boot:run
```

## Inicio de la Aplicación

La aplicación se iniciará en el puerto `8080` por defecto.

## Acceso a la Consola H2

1. Abre un navegador y navega a [http://localhost:8080/h2-console](http://localhost:8080/h2-console).
2. Asegúrate de que el campo **"JDBC URL"** contenga:
 jdbc:h2:mem:testdb (o el URL que aparezca en los logs)
3. Ingresa las siguientes credenciales:
- **Usuario:** `sa`
- **Contraseña:** 

# Notas Importantes

## Ciclo de Vida de la Base de Datos

La base de datos en memoria se crea al iniciar la aplicación y se destruye al detenerla.

## Configuración y Perfiles

- Verifica que el archivo `application.properties` esté en `src/main/resources` y que no exista otro archivo (como `application.yml` o `application-{perfil}.properties`) que pueda sobrescribir la configuración.
- Si usas perfiles (por ejemplo, `dev` o `prod`), asegúrate de activar el correcto usando la variable:

  ```bash
  --spring.profiles.active=dev

# Uso de los Endpoints

## 1. Obtener Empresas con Transferencias del Último Mes
- **Método:** `GET`
- **URL:** [http://localhost:8080/empresas/transferencias-ultimo-mes](http://localhost:8080/empresas/transferencias-ultimo-mes)

## 2. Obtener Empresas con Adhesión del Último Mes
- **Método:** `GET`
- **URL:** [http://localhost:8080/empresas/adhesion-ultimo-mes](http://localhost:8080/empresas/adhesion-ultimo-mes)

## 3. Registrar una Nueva Empresa
- **Método:** `POST`
- **URL:** [http://localhost:8080/empresas/adhesion](http://localhost:8080/empresas/adhesion)
- **Cuerpo (JSON):**

  ```json
  {
    "cuit": "20-12345678-9",
    "razonSocial": "Mi Empresa"
  }

# Supuestos y Consideraciones
## Criterio de “último mes”
- Se interpretó como el período posterior a la fecha actual menos un mes.
- Se toman las transferencias o adhesiones ocurridas a partir de `LocalDate.now().minusMonths(1)`.
## Fecha de Adhesión
- Se asigna en forma automática en el alta del registro.
## Transferencias
- Se agregó el atributo `fechaTransferencia` a la entidad `Transferencia` para poder filtrar según el último mes.
## Validaciones
- Se utiliza la anotación `@Valid` en el controlador para realizar validaciones básicas.
  - Ejemplo: No nulas o de formato.
  - Definidas en la entidad o en DTOs.
  - En este caso, se usan directamente las entidades.
