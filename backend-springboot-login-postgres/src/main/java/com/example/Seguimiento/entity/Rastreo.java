package com.example.Seguimiento.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Rastreo")
public class Rastreo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrastreo")
    private Integer idRastreo;
    

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "idenco")
    private Encomienda encomienda;

    public Integer getIdRastreo() {
        return idRastreo;
    }

    public void setIdRastreo(Integer idRastreo) {
        this.idRastreo = idRastreo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Encomienda getEncomienda() {
        return encomienda;
    }

    public void setEncomienda(Encomienda encomienda) {
        this.encomienda = encomienda;
    }
}
