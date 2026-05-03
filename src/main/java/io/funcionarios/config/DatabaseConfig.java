package io.funcionarios.config;

import io.funcionarios.exception.ConexionBDException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utilidad central para obtener y cerrar conexiones con MySQL.
 *
 * La configuración usa variables de entorno si existen:
 * - DB_URL
 * - DB_USER
 * - DB_PASSWORD
 *
 * Si no existen, usa valores por defecto para desarrollo local.
 */
public final class DatabaseConfig {

    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/db_funcionarios?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASSWORD = "jeronimo110820";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private DatabaseConfig() {
        // Clase utilitaria; no debe instanciarse.
    }

    public static Connection obtenerConexion() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(obtenerUrl(), obtenerUsuario(), obtenerContrasena());
        } catch (ClassNotFoundException e) {
            throw new ConexionBDException("No se encontró el driver de MySQL.", e);
        } catch (SQLException e) {
            throw new ConexionBDException("No fue posible conectarse a la base de datos.", e);
        }
    }

    public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                throw new ConexionBDException("Error al cerrar la conexión.", e);
            }
        }
    }

    public static String obtenerUrl() {
        return obtenerValor("DB_URL", DEFAULT_URL);
    }

    public static String obtenerUsuario() {
        return obtenerValor("DB_USER", DEFAULT_USER);
    }

    public static String obtenerContrasena() {
        return obtenerValor("DB_PASSWORD", DEFAULT_PASSWORD);
    }

    private static String obtenerValor(String nombreVariable, String valorPorDefecto) {
        String valor = System.getenv(nombreVariable);
        if (valor == null || valor.isBlank()) {
            return valorPorDefecto;
        }
        return valor;
    }
}

