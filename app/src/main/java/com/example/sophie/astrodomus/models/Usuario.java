package com.example.sophie.astrodomus.models;

/**
 * Created by Sophie on 5/05/2019.
 */

public class Usuario {

    /**
     * Modelo de Usuario de base de datos.
     *
     */

    private String id_usuario, nombre1, nombre2, apellido1, apellido2, usuario, correo, clave, rol, estado;


    /**
     *
     * @param id_usuario el id del usuario
     * @param nombre1 primer nombre
     * @param nombre2 segundo nombre si se encuestra
     * @param apellido1 primer apellido
     * @param apellido2 segundo apellido
     * @param correo correo
     * @param clave contraseña del usuario
     * @param rol tipo de rol ( LLega el nombre del tipo de rol)
     * @param estado  Estado de activo e inactivo
     */
    public Usuario(String id_usuario, String nombre1, String nombre2, String apellido1, String apellido2, String correo, String clave, String rol, String estado) {
        this.id_usuario = id_usuario;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.correo = correo;
        this.clave = clave;
        this.rol = rol;
        this.estado = estado;
    }

    public Usuario(){

    }

    /**
     * METODOS GET Y SETTERS
     *
     */

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }
}
