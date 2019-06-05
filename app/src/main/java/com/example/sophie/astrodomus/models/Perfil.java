package com.example.sophie.astrodomus.models;

public class Perfil {
    private String id_perfil;
    private String usuario;
    private String ambiente;
    private String tag;

    public Perfil() {
    }

    public Perfil(String id_perfil, String usuario, String ambiente, String tag) {
        this.id_perfil = id_perfil;
        this.usuario = usuario;
        this.ambiente = ambiente;
        this.tag = tag;
    }

    public String getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(String id_perfil) {
        this.id_perfil = id_perfil;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
