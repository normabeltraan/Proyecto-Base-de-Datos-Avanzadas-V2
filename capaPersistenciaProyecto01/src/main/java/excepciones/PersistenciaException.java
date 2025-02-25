/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 * Esta clase exception se encarga de manejar errores relacionados con la capa de persistencia.
 * Esta excepción ocurre comúnmente cuando ocurre un error en la comunicación con la base de datos.
 * Como por ejemplo fallos en la conexión con la base de datos, problemas en la ejecución de consultas, etc.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public class PersistenciaException extends Exception {

    /**
     * Este es el constructor que crea una excepción con un mensaje.
     *
     * @param message El mensaje que describe el error.
     */
    public PersistenciaException(String message) {
        super(message);
    }

    /**
     * El constructor que crea una excepción con un mensaje y la causa de la excepción.
     *
     * @param message El mensaje que describe el error.
     * @param cause La excepción causante del error.
     */
    public PersistenciaException(String message, Throwable cause) {
        super(message, cause);
    }
}
