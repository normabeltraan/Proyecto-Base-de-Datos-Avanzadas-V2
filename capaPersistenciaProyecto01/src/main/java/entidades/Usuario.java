/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author norma
 */
public class Usuario {

    private int id_usuario;
    private String nombre_usuario;
    private String contrasenia;

    public Usuario() {
    }

    public Usuario(int id_usuario, String nombre_usuario, String contrasenia) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.contrasenia = contrasenia;
    }

    public Usuario(String nombre_usuario, String contrasenia) {
        this.nombre_usuario = nombre_usuario;
        this.contrasenia = contrasenia;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id_usuario=" + id_usuario + ", nombre_usuario=" + nombre_usuario + ", contrasenia=" + contrasenia + '}';
    }

}
