package io.funcionarios.service;

import io.funcionarios.dao.EstadoCivilDAO;
import io.funcionarios.dao.FuncionarioDAO;
import io.funcionarios.dao.TipoDocumentoDAO;
import io.funcionarios.exception.DAOException;
import io.funcionarios.model.EstadoCivil;
import io.funcionarios.model.Funcionario;
import io.funcionarios.model.TipoDocumento;

import java.math.BigDecimal;
import java.util.List;

/**
 * Capa de servicios para encapsular validaciones y reglas de negocio.
 */
public class FuncionarioService {

    private final FuncionarioDAO funcionarioDAO;
    private final EstadoCivilDAO estadoCivilDAO;
    private final TipoDocumentoDAO tipoDocumentoDAO;

    public FuncionarioService() {
        this.funcionarioDAO = new FuncionarioDAO();
        this.estadoCivilDAO = new EstadoCivilDAO();
        this.tipoDocumentoDAO = new TipoDocumentoDAO();
    }

    public List<Funcionario> listarFuncionarios() {
        return funcionarioDAO.listar();
    }

    public Funcionario obtenerFuncionario(int idFuncio) {
        Funcionario funcionario = funcionarioDAO.buscarPorId(idFuncio);
        if (funcionario == null) {
            throw new DAOException("No existe un funcionario con el ID " + idFuncio + ".");
        }
        return funcionario;
    }

    public int crearFuncionario(Funcionario funcionario) {
        validarFuncionario(funcionario);
        return funcionarioDAO.crear(funcionario);
    }

    public boolean actualizarFuncionario(Funcionario funcionario) {
        validarFuncionario(funcionario);
        if (funcionario.getIdFuncio() <= 0) {
            throw new DAOException("El ID del funcionario es obligatorio para actualizar.");
        }
        return funcionarioDAO.actualizar(funcionario);
    }

    public boolean eliminarFuncionario(int idFuncio) {
        if (idFuncio <= 0) {
            throw new DAOException("El ID del funcionario debe ser mayor que cero.");
        }
        return funcionarioDAO.eliminar(idFuncio);
    }

    public List<EstadoCivil> listarEstadosCiviles() {
        return estadoCivilDAO.listar();
    }

    public List<TipoDocumento> listarTiposDocumento() {
        return tipoDocumentoDAO.listar();
    }

    private void validarFuncionario(Funcionario funcionario) {
        if (funcionario == null) {
            throw new DAOException("El funcionario no puede ser nulo.");
        }
        if (esTextoVacio(funcionario.getNombre())) {
            throw new DAOException("El nombre es obligatorio.");
        }
        if (esTextoVacio(funcionario.getApellido())) {
            throw new DAOException("El apellido es obligatorio.");
        }
        if (esTextoVacio(funcionario.getNumeroDocumento())) {
            throw new DAOException("El número de documento es obligatorio.");
        }
        if (funcionario.getTipoDocumento() == null || funcionario.getTipoDocumento().getIdTipoDocumento() <= 0) {
            throw new DAOException("Debe seleccionar un tipo de documento válido.");
        }
        if (funcionario.getEstadoCivil() == null || funcionario.getEstadoCivil().getIdEstado() <= 0) {
            throw new DAOException("Debe seleccionar un estado civil válido.");
        }
        if (funcionario.getSalario() == null || funcionario.getSalario().compareTo(BigDecimal.ZERO) <= 0) {
            throw new DAOException("El salario debe ser mayor que cero.");
        }
        if (funcionario.getFechaIngreso() == null) {
            throw new DAOException("La fecha de ingreso es obligatoria.");
        }
    }

    private boolean esTextoVacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }
}

