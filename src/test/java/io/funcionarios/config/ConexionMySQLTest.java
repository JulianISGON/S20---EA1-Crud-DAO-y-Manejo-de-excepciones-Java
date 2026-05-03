package io.funcionarios.config;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Prueba de integración opcional para validar la conexión real con MySQL.
 * Para ejecutarla, define la variable de entorno:
 * DB_RUN_INTEGRATION_TESTS=true
 */
class ConexionMySQLTest {

    @Test
    void debeConectarAlaBaseDeDatosCuandoLaPruebaEsteHabilitada() throws Exception {
        Assumptions.assumeTrue(
                "true".equalsIgnoreCase(System.getenv("DB_RUN_INTEGRATION_TESTS")),
                "Prueba de integración deshabilitada por defecto"
        );

        try (Connection conexion = DatabaseConfig.obtenerConexion()) {
            assertNotNull(conexion);
            assertTrue(conexion.isValid(2));
        }
    }
}


