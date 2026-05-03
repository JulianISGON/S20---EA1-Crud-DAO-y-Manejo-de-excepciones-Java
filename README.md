# CRUD DAO Funcionarios - Proyecto Formativo

## Contexto académico
Este proyecto fue desarrollado como ejercicio **100% formativo** en la **IU Digital de Antioquia**.

Su propósito principal es aplicar, en un caso realista, conceptos de:
- arquitectura por capas,
- patrón DAO,
- conexión a base de datos con JDBC,
- validación de reglas de negocio,
- y manejo de excepciones personalizadas.

## Objetivo general
Construir una aplicación de escritorio en Java para gestionar funcionarios mediante operaciones CRUD, separando responsabilidades en capas para mejorar mantenibilidad, legibilidad y escalabilidad del código.

## Objetivos específicos
- Implementar una interfaz de usuario en Swing para listar, crear, editar y eliminar funcionarios.
- Aplicar el patrón DAO para encapsular el acceso a datos y consultas SQL.
- Centralizar la conexión a MySQL en una clase de configuración reutilizable.
- Incluir validaciones de negocio en una capa de servicio.
- Manejar errores de conexión y de persistencia con excepciones personalizadas.

## Arquitectura del proyecto
El proyecto está organizado con una arquitectura por capas:

1. **UI (Presentación)**
   - `src/main/java/io/funcionarios/ui/VentanaPrincipal.java`
   - `src/main/java/io/funcionarios/ui/DialogoFuncionario.java`
   - Responsabilidad: interacción con el usuario, renderizado de tabla, formularios y mensajes.

2. **Service (Lógica de negocio)**
   - `src/main/java/io/funcionarios/service/FuncionarioService.java`
   - Responsabilidad: validaciones funcionales (campos obligatorios, salario, fecha, IDs válidos) y coordinación entre UI y DAO.

3. **DAO (Acceso a datos)**
   - `src/main/java/io/funcionarios/dao/IFuncionarioDAO.java`
   - `src/main/java/io/funcionarios/dao/FuncionarioDAO.java`
   - `src/main/java/io/funcionarios/dao/EstadoCivilDAO.java`
   - `src/main/java/io/funcionarios/dao/TipoDocumentoDAO.java`
   - Responsabilidad: ejecutar SQL con JDBC y mapear resultados a objetos de dominio.

4. **Config (Conectividad)**
   - `src/main/java/io/funcionarios/config/DatabaseConfig.java`
   - Responsabilidad: obtener conexión JDBC a MySQL y leer parámetros de entorno (`DB_URL`, `DB_USER`, `DB_PASSWORD`).

5. **Model (Dominio)**
   - `src/main/java/io/funcionarios/model/Funcionario.java`
   - `src/main/java/io/funcionarios/model/EstadoCivil.java`
   - `src/main/java/io/funcionarios/model/TipoDocumento.java`
   - (Extensiones modeladas: `GrupoFamiliar`, `FormacionAcademica`)

6. **Exception (Manejo de errores)**
   - `src/main/java/io/funcionarios/exception/DAOException.java`
   - `src/main/java/io/funcionarios/exception/ConexionBDException.java`

## Tecnologías utilizadas
- **Java 17**
- **Gradle**
- **Swing** (interfaz gráfica)
- **MySQL 8+**
- **JDBC**
- **JUnit 5** (pruebas)
- **SLF4J** (logging)

## Conexión a base de datos
La conexión se centraliza en `DatabaseConfig`.

Orden de configuración:
1. Si existen variables de entorno, se usan:
   - `DB_URL`
   - `DB_USER`
   - `DB_PASSWORD`
2. Si no existen, se usan valores por defecto definidos en código para entorno local.

El esquema SQL y datos de ejemplo están en:
- `src/main/resources/database_schema.sql`

## Lógica funcional del CRUD
Flujo general de una operación:
1. La UI captura la acción del usuario (crear/editar/eliminar/listar).
2. La UI invoca métodos de `FuncionarioService`.
3. `FuncionarioService` valida reglas de negocio.
4. Si es válido, delega a `FuncionarioDAO`.
5. El DAO ejecuta SQL y retorna resultado a la capa superior.
6. La UI actualiza tabla y muestra mensajes.

## Manejo de excepciones
- `ConexionBDException`: errores de conexión (driver, credenciales, disponibilidad de BD).
- `DAOException`: errores de acceso a datos y validaciones funcionales.

Esto permite mensajes claros al usuario y separación limpia entre errores técnicos y de lógica del negocio.

## Logros de aprendizaje obtenidos
Durante este proyecto se fortalecieron habilidades en:
- diseño de software por capas,
- implementación del patrón DAO,
- mapeo objeto-relacional manual con JDBC,
- validación de datos en capa de servicio,
- manejo estructurado de excepciones,
- uso de Gradle para compilación y pruebas,
- y documentación técnica orientada a contexto académico.

## Ejecución del proyecto
Para compilar y ejecutar pruebas:

```powershell
Set-Location "C:\Users\julia\OneDrive\Desktop\julian\IUDigital\2026-01\software seguro\S20 - EA1 Crud DAO y Manejo de excepciones Java"
.\gradlew clean test
```

Para ejecutar la aplicación:

```powershell
Set-Location "C:\Users\julia\OneDrive\Desktop\julian\IUDigital\2026-01\software seguro\S20 - EA1 Crud DAO y Manejo de excepciones Java"
.\gradlew run
```

También puedes revisar la guía operativa en:
- `EJECUTAR_PROYECTO.md`

## Alcance y naturaleza del proyecto
Este repositorio corresponde a una práctica universitaria con fines de aprendizaje.

**Declaración institucional:**
> Este es un proyecto **100% formativo** de la **IU Digital de Antioquia**.

## Política de documentación
En la raíz del proyecto solo se mantienen estos archivos Markdown:
1. `README.md`
2. `AGENTS.md`
3. `EJECUTAR_PROYECTO.md`
