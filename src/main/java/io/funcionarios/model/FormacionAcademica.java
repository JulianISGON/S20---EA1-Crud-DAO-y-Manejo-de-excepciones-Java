package io.funcionarios.model;

/**
 * Representa una formación académica asociada a un funcionario.
 */
public class FormacionAcademica {

    private int idFormacion;
    private Funcionario funcionario;
    private String titulo;
    private String institucion;
    private Integer anoGraduacion;

    public FormacionAcademica() {
    }

    public FormacionAcademica(int idFormacion, Funcionario funcionario, String titulo,
                              String institucion, Integer anoGraduacion) {
        this.idFormacion = idFormacion;
        this.funcionario = funcionario;
        this.titulo = titulo;
        this.institucion = institucion;
        this.anoGraduacion = anoGraduacion;
    }

    public int getIdFormacion() {
        return idFormacion;
    }

    public void setIdFormacion(int idFormacion) {
        this.idFormacion = idFormacion;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public Integer getAnoGraduacion() {
        return anoGraduacion;
    }

    public void setAnoGraduacion(Integer anoGraduacion) {
        this.anoGraduacion = anoGraduacion;
    }
}

