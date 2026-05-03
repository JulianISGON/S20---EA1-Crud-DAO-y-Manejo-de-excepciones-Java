package io.funcionarios.model;

/**
 * Representa el estado civil de un funcionario.
 */
public class EstadoCivil {

    private int idEstado;
    private String descripcion;
    private String abreviatura;

    public EstadoCivil() {
    }

    public EstadoCivil(int idEstado, String descripcion, String abreviatura) {
        this.idEstado = idEstado;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}

