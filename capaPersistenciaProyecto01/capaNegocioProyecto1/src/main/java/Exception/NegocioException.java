/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exception;

/**
 * Clase Negocio Exception
 * @author Maximiliano
 */
public class NegocioException extends Exception
{
    /**
     * Constructor que se encargar de lanzar un mensaje.
     * @param message El mensaje que se lanza.
     */
    public NegocioException(String message)
    {
        super(message);
    }
    
    /**
     * Constructor que se encarga de lanzar un mensaje y una causa.
     * @param message El mensaje que se lanza.
     * @param cause La causa que se lanza.
     */
    public NegocioException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
