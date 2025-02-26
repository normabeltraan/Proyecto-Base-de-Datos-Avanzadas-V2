/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 * Esta clase representa un objeto de transferencia de datos (DTO) para la entidad Usuario.
 * Se utiliza para transportar información de usuarios entre las distintas capas del sistema.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public class UsuarioDTO 
{
    private int id_usuario;
    private String nombre_usuario;
    private String contrasenia;

    /**
     * El constructor por defecto, crea la instancia vacía de la clase.
     */
    public UsuarioDTO() {
    }

    /**
     * Constructor que inicializa todos los atributos incluyendo el identificador único del usuario.
     * @param id_usuario El identificador único del usuario.
     * @param nombre_usuario El nombre del usuario.
     * @param contrasenia La contraseña del usuario.
     */
    public UsuarioDTO(int id_usuario, String nombre_usuario, String contrasenia) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.contrasenia = contrasenia;
    }

    /**
     * Constructor que inicializa todos los atributos excepto el identificador único de usuario.
     * @param nombre_usuario El nombre del usuario.
     * @param contrasenia La contraseña del usuario.
     */
    public UsuarioDTO(String nombre_usuario, String contrasenia) {
        this.nombre_usuario = nombre_usuario;
        this.contrasenia = contrasenia;
    }

    /**
     * Se obtiene el identificador único del usuario.
     * @return Regresa el identificador único del usuario.
     */
    public int getId_usuario() {
        return id_usuario;
    }

    /**
     * Se establece el identificador único del usuario.
     * @param id_usuario El identificador único del usuario.
     */
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    /**
     * Se obtiene el nombre de usuario.
     * @return Regresa el nombre del usuario.
     */
    public String getNombre_usuario() {
        return nombre_usuario;
    }

    /**
     * Se establece el nombre del usuario.
     * @param nombre_usuario El nombre del usuario.
     */
    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    /**
     * Se obtiene la contraseña del usuario.
     * @return Regresa la contraseña del usuario.
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Se establece la contraseña del usuario.
     * @param contrasenia La contraseña del usuario.
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * Representación en cadena de la información del usuario.
     * @return Regresa una cadena de texto con la información del usuario.
     */
    @Override
    public String toString() {
        return "UsuarioDTO{" + "id_usuario=" + id_usuario + ", nombre_usuario=" + nombre_usuario + ", contrasenia=" + contrasenia + '}';
    }
}
