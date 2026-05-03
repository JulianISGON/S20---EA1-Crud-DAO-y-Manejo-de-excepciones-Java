package io.funcionarios.exception;

/**
 * Excepción personalizada para errores de conexión con la base de datos.
 */
public class ConexionBDException extends RuntimeException {

    public ConexionBDException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}

