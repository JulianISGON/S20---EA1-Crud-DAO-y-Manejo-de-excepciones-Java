package io.funcionarios.exception;

/**
 * Excepción general para errores en el acceso a datos.
 */
public class DAOException extends RuntimeException {

    public DAOException(String mensaje) {
        super(mensaje);
    }

    public DAOException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}

