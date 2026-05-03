package io.funcionarios.dao;

import io.funcionarios.config.DatabaseConfig;
import io.funcionarios.exception.DAOException;
import io.funcionarios.model.EstadoCivil;
import io.funcionarios.model.Funcionario;
import io.funcionarios.model.TipoDocumento;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación JDBC para CRUD de funcionarios.
 */
public class FuncionarioDAO implements IFuncionarioDAO {

    @Override
    public List<Funcionario> listar() {
        String sql = """
                SELECT f.id_funcio, f.nombre, f.apellido, f.numero_documento,
                       f.salario, f.fecha_ingreso, f.email, f.telefono,
                       td.id_tipo_doc, td.descripcion AS td_descripcion, td.abreviatura AS td_abreviatura,
                       ec.id_estado, ec.descripcion AS ec_descripcion, ec.abreviatura AS ec_abreviatura
                FROM funcionarios f
                INNER JOIN tipo_documento td ON f.id_tipo_documento = td.id_tipo_doc
                INNER JOIN estado_civil ec ON f.id_estado_civil = ec.id_estado
                ORDER BY f.apellido, f.nombre
                """;

        List<Funcionario> funcionarios = new ArrayList<>();

        try (Connection conexion = DatabaseConfig.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                funcionarios.add(mapearFuncionario(rs));
            }
            return funcionarios;
        } catch (SQLException e) {
            throw new DAOException("Error al listar funcionarios.", e);
        }
    }

    @Override
    public Funcionario buscarPorId(int idFuncio) {
        String sql = """
                SELECT f.id_funcio, f.nombre, f.apellido, f.numero_documento,
                       f.salario, f.fecha_ingreso, f.email, f.telefono,
                       td.id_tipo_doc, td.descripcion AS td_descripcion, td.abreviatura AS td_abreviatura,
                       ec.id_estado, ec.descripcion AS ec_descripcion, ec.abreviatura AS ec_abreviatura
                FROM funcionarios f
                INNER JOIN tipo_documento td ON f.id_tipo_documento = td.id_tipo_doc
                INNER JOIN estado_civil ec ON f.id_estado_civil = ec.id_estado
                WHERE f.id_funcio = ?
                """;

        try (Connection conexion = DatabaseConfig.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idFuncio);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearFuncionario(rs);
                }
            }
            return null;
        } catch (SQLException e) {
            throw new DAOException("Error al buscar funcionario por ID.", e);
        }
    }

    @Override
    public int crear(Funcionario funcionario) {
        String sql = """
                INSERT INTO funcionarios
                (nombre, apellido, numero_documento, id_tipo_documento, id_estado_civil,
                 salario, fecha_ingreso, email, telefono)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conexion = DatabaseConfig.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            cargarParametros(funcionario, ps);
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new DAOException("No se pudo crear el funcionario.");
            }

            try (ResultSet claves = ps.getGeneratedKeys()) {
                if (claves.next()) {
                    return claves.getInt(1);
                }
            }
            throw new DAOException("No se obtuvo el ID generado del funcionario.");
        } catch (SQLException e) {
            throw new DAOException("Error al crear funcionario.", e);
        }
    }

    @Override
    public boolean actualizar(Funcionario funcionario) {
        String sql = """
                UPDATE funcionarios
                SET nombre = ?, apellido = ?, numero_documento = ?, id_tipo_documento = ?,
                    id_estado_civil = ?, salario = ?, fecha_ingreso = ?, email = ?, telefono = ?
                WHERE id_funcio = ?
                """;

        try (Connection conexion = DatabaseConfig.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            cargarParametros(funcionario, ps);
            ps.setInt(10, funcionario.getIdFuncio());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Error al actualizar funcionario.", e);
        }
    }

    @Override
    public boolean eliminar(int idFuncio) {
        String sql = "DELETE FROM funcionarios WHERE id_funcio = ?";

        try (Connection conexion = DatabaseConfig.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idFuncio);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Error al eliminar funcionario.", e);
        }
    }

    private void cargarParametros(Funcionario funcionario, PreparedStatement ps) throws SQLException {
        ps.setString(1, funcionario.getNombre());
        ps.setString(2, funcionario.getApellido());
        ps.setString(3, funcionario.getNumeroDocumento());
        ps.setInt(4, funcionario.getTipoDocumento().getIdTipoDocumento());
        ps.setInt(5, funcionario.getEstadoCivil().getIdEstado());
        ps.setBigDecimal(6, funcionario.getSalario());
        ps.setDate(7, Date.valueOf(funcionario.getFechaIngreso()));
        ps.setString(8, funcionario.getEmail());
        ps.setString(9, funcionario.getTelefono());
    }

    private Funcionario mapearFuncionario(ResultSet rs) throws SQLException {
        TipoDocumento tipoDocumento = new TipoDocumento(
                rs.getInt("id_tipo_doc"),
                rs.getString("td_descripcion"),
                rs.getString("td_abreviatura")
        );

        EstadoCivil estadoCivil = new EstadoCivil(
                rs.getInt("id_estado"),
                rs.getString("ec_descripcion"),
                rs.getString("ec_abreviatura")
        );

        return new Funcionario(
                rs.getInt("id_funcio"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("numero_documento"),
                tipoDocumento,
                estadoCivil,
                rs.getBigDecimal("salario"),
                rs.getDate("fecha_ingreso").toLocalDate(),
                rs.getString("email"),
                rs.getString("telefono")
        );
    }
}

