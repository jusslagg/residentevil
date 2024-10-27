# API REST PARA RESIDENT EVIL CON SPRING BOOT 🔫🧟‍♂️

ESTE MODELO AÚN ESTÁ EN DESARROLLO PERO YA SE PUEDEN PROBAR LOS MÉTODOS MÁS COMUNES DE UNA API CON DATA RELACIONAL.

**Nota:** La base de datos no tiene datos embebidos.

**Nota:** PROBADO EN POSTMAN.

## Importante

LOS OBJETOS DEBEN MANDARSE A POSTMAN COMO SE DESCRIBE EN EL README. De lo contrario, podría causar malas relaciones en la base de datos y los datos podrían no eliminarse correctamente.

---

## Clase Personaje 🦸‍♂️🦸‍♀️

### Obtener personajes
- **Método:** GET
- **URL:** `/api/personajes`
- **Descripción:** Retorna todos los personajes disponibles en la base de datos.

GET http://localhost:8080/api/personajes

### Obtener personaje por ID
- **Método:** GET
- **URL:** `/api/personajes/{ID}`
- **Descripción:** Retorna el personaje por ID.

(Cambiar 1 por el ID existente)

### Crear personaje
- **Método:** POST
- **URL:** `/api/personajes/createPersonaje`
- **Descripción:** Crea un nuevo personaje en la base de datos.


POST http://localhost:8080/api/personajes/createPersonaje

Body: Seleccionar raw y luego JSON para ingresar el cuerpo de la solicitud.

###Actualizar personaje existente
Método: PUT
URL: /api/personajes/{id}
Descripción: Actualiza un personaje existente con los nuevos datos proporcionados.

###Eliminar un personaje
Método: DELETE
URL: /api/personajes/{id}
Descripción: Elimina un personaje de la base de datos.

# Clase Enemigo 🧟‍♂️
Obtener enemigos
Método: GET
URL: /api/enemigos
Descripción: Retorna todos los enemigos disponibles en la base de datos.

### Obtener enemigo por ID
Método: GET
URL: /api/enemigos/{id}
Descripción: Devuelve el enemigo correspondiente al ID proporcionado. Retorna un 404 Not Found si no existe.

### Crear enemigo
Método: POST
URL: /api/enemigos/createEnemigo
Descripción: Crea un nuevo enemigo con la información proporcionada en el cuerpo de la solicitud.

### Eliminar un enemigo
Método: DELETE
URL: /api/enemigos/{id}
Descripción: Elimina el enemigo correspondiente al ID proporcionado.

## Consideraciones
La API está diseñada para interactuar con una base de datos H2 en memoria.
Asegúrate de que el servidor esté en funcionamiento y de que la base de datos esté configurada correctamente para evitar errores de conexión.
