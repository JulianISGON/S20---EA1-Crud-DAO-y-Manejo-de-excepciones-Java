package io.funcionarios.dao;

import io.funcionarios.model.Funcionario;

import java.util.List;

/**
 * Contrato CRUD para la entidad Funcionario.
 */
public interface IFuncionarioDAO {

    List<Funcionario> listar();

    Funcionario buscarPorId(int idFuncio);

    int crear(Funcionario funcionario);

    boolean actualizar(Funcionario funcionario);

    boolean eliminar(int idFuncio);
}

