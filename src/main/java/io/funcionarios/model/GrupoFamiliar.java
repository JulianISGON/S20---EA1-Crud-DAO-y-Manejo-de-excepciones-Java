package io.funcionarios.model;

import java.time.LocalDate;

/**
 * Representa a un integrante del grupo familiar asociado a un funcionario.
 */
public class GrupoFamiliar {

    private int idGrupo;
    private Funcionario funcionario;
    private String nombre;
    private String relacion;
    private LocalDate fechaNacimiento;
    private String numeroDocumento;

    public GrupoFamiliar() {
    }

    public GrupoFamiliar(int idGrupo, Funcionario funcionario, String nombre, String relacion,
                         LocalDate fechaNacimiento, String numeroDocumento) {
        this.idGrupo = idGrupo;
        this.funcionario = funcionario;
        this.nombre = nombre;
        this.relacion = relacion;
        this.fechaNacimiento = fechaNacimiento;
        this.numeroDocumento = numeroDocumento;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRelacion() {
        return relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
}

