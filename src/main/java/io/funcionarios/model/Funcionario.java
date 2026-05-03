package io.funcionarios.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Representa a un funcionario de la entidad.
 */
public class Funcionario {

    private int idFuncio;
    private String nombre;
    private String apellido;
    private String numeroDocumento;
    private TipoDocumento tipoDocumento;
    private EstadoCivil estadoCivil;
    private BigDecimal salario;
    private LocalDate fechaIngreso;
    private String email;
    private String telefono;

    public Funcionario() {
    }

    public Funcionario(int idFuncio, String nombre, String apellido, String numeroDocumento,
                       TipoDocumento tipoDocumento, EstadoCivil estadoCivil,
                       BigDecimal salario, LocalDate fechaIngreso, String email, String telefono) {
        this.idFuncio = idFuncio;
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroDocumento = numeroDocumento;
        this.tipoDocumento = tipoDocumento;
        this.estadoCivil = estadoCivil;
        this.salario = salario;
        this.fechaIngreso = fechaIngreso;
        this.email = email;
        this.telefono = telefono;
    }

    public int getIdFuncio() {
        return idFuncio;
    }

    public void setIdFuncio(int idFuncio) {
        this.idFuncio = idFuncio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "idFuncio=" + idFuncio +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                '}';
    }
}

