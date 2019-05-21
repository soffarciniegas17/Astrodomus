package com.example.sophie.astrodomus.models;

/**
 * Created by Sophie on 17/05/2019.
 */

public class Reporte {

    private String id_reporte, nombre, tipoReporte, tipoReporteDet, ambiente, hora, fecha, detalle;

    public Reporte(String id_reporte, String nombre, String tipoReporte, String tipoReporteDet, String ambiente, String hora, String fecha, String detalle) {
        this.id_reporte = id_reporte;
        this.nombre = nombre;
        this.tipoReporte = tipoReporte;
        this.ambiente = ambiente;
        this.hora = hora;
        this.fecha = fecha;
        this.detalle = detalle;
        this.tipoReporteDet = tipoReporteDet;
    }

    public String getTipoReporteDet() {
        return tipoReporteDet;
    }

    public void setTipoReporteDet(String tipoReporteDet) {
        this.tipoReporteDet = tipoReporteDet;
    }

    public String getId_reporte() {
        return id_reporte;
    }

    public void setId_reporte(String id_reporte) {
        this.id_reporte = id_reporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
