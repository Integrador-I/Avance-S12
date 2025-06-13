package com.example.Seguimiento.dto;

public class RastreoDTO
{
    private String codigo;
    private String descripcionRastreo;
    private String estadoEncomienda;
    private String descripcionEncomienda;
    private String nombreCliente;
    private String destino;

    public RastreoDTO(String codigo, String descripcionRastreo, String estadoEncomienda, String descripcionEncomienda, String nombreCliente, String destino) {
        this.codigo = codigo;
        this.descripcionRastreo = descripcionRastreo;
        this.estadoEncomienda = estadoEncomienda;
        this.descripcionEncomienda = descripcionEncomienda;
        this.nombreCliente = nombreCliente;
        this.destino = destino;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcionRastreo() {
        return descripcionRastreo;
    }

    public void setDescripcionRastreo(String descripcionRastreo) {
        this.descripcionRastreo = descripcionRastreo;
    }

    public String getEstadoEncomienda() {
        return estadoEncomienda;
    }

    public void setEstadoEncomienda(String estadoEncomienda) {
        this.estadoEncomienda = estadoEncomienda;
    }

    public String getDescripcionEncomienda() {
        return descripcionEncomienda;
    }

    public void setDescripcionEncomienda(String descripcionEncomienda) {
        this.descripcionEncomienda = descripcionEncomienda;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
}
