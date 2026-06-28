# Prueba Técnica - Stefanini

API REST construida con Spring Boot que consume la API pública de [PetStore](https://petstore.swagger.io/v2/) para gestionar mascotas.

## Tecnologías

- Java 17
- Spring Boot 3.5.16
- Gradle
- Lombok
- Spring Boot Starter Validation
- Spring WebFlux (RestClient)

## Estructura del proyecto

```
src/main/java/com/stefanini/pruebatecnica/
├── config/
│   └── SessionFilter.java          # Filtro de trazabilidad con X-Transaction-Id
├── clients/
│   └── PetClient.java              # Cliente HTTP para consumir PetStore API
├── controller/
│   ├── PetController.java          # Endpoints REST para mascotas
│   └── HolaMundoController.java    # Endpoint de prueba
├── dto/
│   ├── Pet.java                    # Modelo simple de mascota
│   ├── PetDTO.java                 # DTO completo con categorías y tags
│   ├── CategoryDTO.java            # DTO de categoría
│   ├── TagDTO.java                 # DTO de tag
│   └── ApiResponse.java            # Respuesta estandarizada con transactionId
├── service/
│   ├── PetService.java             # Interfaz de servicio
│   └── impl/
│       └── PetServiceImpl.java     # Implementación del servicio
└── PruebatecnicaApplication.java   # Clase principal
```

## Endpoints

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/pet/{petId}` | Obtiene una mascota por ID (mock) |
| GET | `/api/pet/client/{petId}` | Obtiene una mascota desde PetStore API |
| POST | `/api/pet` | Guarda una mascota (mock) |
| POST | `/api/pet/client` | Guarda una mascota en PetStore API |

## Configuración

El contexto de la aplicación está configurado en `/api`:

```yaml
server:
  servlet:
    context-path: /api
```

### Trazabilidad

Cada request genera automáticamente un `X-Transaction-Id` (UUID) que se:
- Agrega al MDC para logging
- Retorna en el header de respuesta
- Se incluye en la respuesta `ApiResponse`

## Ejecutar la aplicación

```bash
# Usando Gradle Wrapper
./gradlew bootRun

# O compilar y ejecutar
./gradlew build
java -jar build/libs/pruebatecnica-0.0.1-SNAPSHOT.jar
```

La aplicación iniciará en `http://localhost:8080`

## Ejemplos de uso

### GET `/api/pet/{petId}` - Obtener mascota (mock)

Returns una mascota hardcoded sin consumir el servicio externo.

```bash
curl http://localhost:8080/api/pet/1
```

**Response:**
```json
{
  "id": 1,
  "name": "testingPet1",
  "status": "available"
}
```

---

### GET `/api/pet/client/{petId}` - Obtener mascota desde PetStore API

Consulta la mascota directamente en la API de PetStore.

```bash
curl http://localhost:8080/api/pet/client/1
```

**Response:**
```json
{
  "id": 1,
  "category": {
    "id": 1,
    "name": "Dogs"
  },
  "name": "Rex",
  "photoUrls": [
    "https://example.com/rex.jpg"
  ],
  "tags": [
    {
      "id": 1,
      "name": "friendly"
    }
  ],
  "status": "available"
}
```

---

### POST `/api/pet` - Guardar mascota (mock)

Returns una respuesta estandarizada sin persistir datos.

```bash
curl -X POST http://localhost:8080/api/pet \
  -H "Content-Type: application/json" \
  -d '{
    "id": 1,
    "name": "mi-mascota",
    "status": "available"
  }'
```

**Response:**
```json
{
  "transactionId": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
  "dateCreated": "2026-06-27T12:00:00Z",
  "status": "available",
  "name": "mi-mascota"
}
```

---

### POST `/api/pet/client` - Guardar mascota en PetStore API

Persiste la mascota en el servicio externo de PetStore.

```bash
curl -X POST http://localhost:8080/api/pet/client \
  -H "Content-Type: application/json" \
  -d '{
    "id": 1002,
    "category": {
      "id": 1,
      "name": "Ave"
    },
    "name": "Perico",
    "photoUrls": [
      "C:/imagenes/2"
    ],
    "tags": [
      {
        "id": 1,
        "name": "volador"
      }
    ],
    "status": "available"
  }'
```

**Response:**
```json
{
  "transactionId": "f9e8d7c6-b5a4-3210-fedc-ba9876543210",
  "dateCreated": "2026-06-27T12:05:00Z",
  "status": "available",
  "name": "Perico"
}
```

## Testing

```bash
./gradlew test
```
