# Cómo ejecutar el proyecto

## Requisitos
- Java 17
- Gradle
- MySQL Server 8+

## Paso a paso
1. Abrir una terminal en la raíz del proyecto.
2. Compilar el proyecto:

```powershell
.\gradlew build
```

3. Ejecutar el script SQL ubicado en `src/main/resources/database_schema.sql` en MySQL.
4. Para arrancar la aplicación con sus dependencias, usar:

```powershell
.\gradlew run
```

5. Si prefieres una carpeta lista para ejecutar en Windows:

```powershell
.\gradlew installDist
.\build\install\DAO\bin\DAO.bat
```

## Verificación
- Confirmar que la base de datos `db_funcionarios` existe.
- Confirmar que las tablas fueron creadas.
- Confirmar que hay datos iniciales cargados.
- Confirmar que la ventana Swing abre y carga la tabla de funcionarios.

## Nota
Este archivo se mantiene breve a propósito para evitar documentación duplicada.
