package io.funcionarios;

import io.funcionarios.config.DatabaseConfig;
import io.funcionarios.exception.ConexionBDException;

import java.sql.Connection;

/**
 * Pequeño programa de prueba para validar la conexión con MySQL.
 */
public class TestConexion {

    public static void main(String[] args) {
        System.out.println("=== PRUEBA DE CONEXIÓN A MYSQL ===");
        System.out.println("URL: " + DatabaseConfig.obtenerUrl());
        System.out.println("Usuario: " + DatabaseConfig.obtenerUsuario());

        try (Connection conexion = DatabaseConfig.obtenerConexion()) {
            System.out.println("Conexión establecida correctamente.");
            System.out.println("¿La conexión es válida? " + conexion.isValid(2));
        } catch (ConexionBDException | java.sql.SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

