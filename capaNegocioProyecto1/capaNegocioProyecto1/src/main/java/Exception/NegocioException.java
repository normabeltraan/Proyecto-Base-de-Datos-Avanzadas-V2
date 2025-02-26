/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exception;

/**
 * Esta clase exception se encarga de manejar los errores que ocurran en la capa negocio.
 * Esto es más que nada para manejar errores específicos en la lógica de negocio en el sistema.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
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
