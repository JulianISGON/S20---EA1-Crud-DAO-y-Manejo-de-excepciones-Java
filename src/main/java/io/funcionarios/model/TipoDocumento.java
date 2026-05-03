package io.funcionarios.model;

/**
 * Representa un tipo de documento de identificación.
 */
public class TipoDocumento {

    private int idTipoDocumento;
    private String descripcion;
    private String abreviatura;

    public TipoDocumento() {
    }

    public TipoDocumento(int idTipoDocumento, String descripcion, String abreviatura) {
        this.idTipoDocumento = idTipoDocumento;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
    }

    public int getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(int idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
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

