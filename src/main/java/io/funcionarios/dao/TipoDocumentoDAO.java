package io.funcionarios.dao;

import io.funcionarios.config.DatabaseConfig;
import io.funcionarios.exception.DAOException;
import io.funcionarios.model.TipoDocumento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO de solo lectura para tipos de documento.
 */
public class TipoDocumentoDAO {

    public List<TipoDocumento> listar() {
        String sql = "SELECT id_tipo_doc, descripcion, abreviatura FROM tipo_documento ORDER BY descripcion";
        List<TipoDocumento> tipos = new ArrayList<>();

        try (Connection conexion = DatabaseConfig.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                tipos.add(new TipoDocumento(
                        rs.getInt("id_tipo_doc"),
                        rs.getString("descripcion"),
                        rs.getString("abreviatura")
                ));
            }
            return tipos;
        } catch (SQLException e) {
            throw new DAOException("Error al listar tipos de documento.", e);
        }
    }
}

