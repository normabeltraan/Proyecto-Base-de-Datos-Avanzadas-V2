/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author norma
 */
public class PersistenciaException extends Exception {

    /**
     * Constructor con mensaje
     *
     * @param message mensaje
     */
    public PersistenciaException(String message) {
        super(message);
    }

    /**
     * Constructor con mensaje y la causa de la excepción
     *
     * @param message mensaje
     * @param cause causa
     */
    public PersistenciaException(String message, Throwable cause) {
        super(message, cause);
    }
}
