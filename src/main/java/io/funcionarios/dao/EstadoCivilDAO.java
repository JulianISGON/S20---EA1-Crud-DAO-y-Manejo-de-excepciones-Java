package io.funcionarios.dao;

import io.funcionarios.config.DatabaseConfig;
import io.funcionarios.exception.DAOException;
import io.funcionarios.model.EstadoCivil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO de solo lectura para estados civiles.
 */
public class EstadoCivilDAO {

    public List<EstadoCivil> listar() {
        String sql = "SELECT id_estado, descripcion, abreviatura FROM estado_civil ORDER BY descripcion";
        List<EstadoCivil> estados = new ArrayList<>();

        try (Connection conexion = DatabaseConfig.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                estados.add(new EstadoCivil(
                        rs.getInt("id_estado"),
                        rs.getString("descripcion"),
                        rs.getString("abreviatura")
                ));
            }
            return estados;
        } catch (SQLException e) {
            throw new DAOException("Error al listar estados civiles.", e);
        }
    }
}

