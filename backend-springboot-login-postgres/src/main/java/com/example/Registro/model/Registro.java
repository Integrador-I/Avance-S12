package com.example.Registro.model;

import jakarta.persistence.*;

@Entity
@Table(name = "registro")
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Remitente
    @Column(name = "remitente_dni")
    private String remitenteDNI;

    @Column(name = "remitente_nombre")
    private String remitenteNombre;

    @Column(name = "remitente_apellido_paterno")
    private String remitenteApellidoPaterno;

    @Column(name = "remitente_apellido_materno")
    private String remitenteApellidoMaterno;

    @Column(name = "remitente_celular")
    private String remitenteCelular;

    // Destinatario
    @Column(name = "destinatario_dni")
    private String destinatarioDNI;

    @Column(name = "destinatario_nombre")
    private String destinatarioNombre;

    @Column(name = "destinatario_apellido_paterno")
    private String destinatarioApellidoPaterno;

    @Column(name = "destinatario_apellido_materno")
    private String destinatarioApellidoMaterno;

    @Column(name = "destinatario_celular")
    private String destinatarioCelular;

    // Tipo de paquete
    @Column(name = "tipo_paquete")
    private String tipoPaquete;

    // Getters y Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRemitenteDNI() { return remitenteDNI; }
    public void setRemitenteDNI(String remitenteDNI) { this.remitenteDNI = remitenteDNI; }

    public String getRemitenteNombre() { return remitenteNombre; }
    public void setRemitenteNombre(String remitenteNombre) { this.remitenteNombre = remitenteNombre; }

    public String getRemitenteApellidoPaterno() { return remitenteApellidoPaterno; }
    public void setRemitenteApellidoPaterno(String remitenteApellidoPaterno) { this.remitenteApellidoPaterno = remitenteApellidoPaterno; }

    public String getRemitenteApellidoMaterno() { return remitenteApellidoMaterno; }
    public void setRemitenteApellidoMaterno(String remitenteApellidoMaterno) { this.remitenteApellidoMaterno = remitenteApellidoMaterno; }

    public String getRemitenteCelular() { return remitenteCelular; }
    public void setRemitenteCelular(String remitenteCelular) { this.remitenteCelular = remitenteCelular; }

    public String getDestinatarioDNI() { return destinatarioDNI; }
    public void setDestinatarioDNI(String destinatarioDNI) { this.destinatarioDNI = destinatarioDNI; }

    public String getDestinatarioNombre() { return destinatarioNombre; }
    public void setDestinatarioNombre(String destinatarioNombre) { this.destinatarioNombre = destinatarioNombre; }

    public String getDestinatarioApellidoPaterno() { return destinatarioApellidoPaterno; }
    public void setDestinatarioApellidoPaterno(String destinatarioApellidoPaterno) { this.destinatarioApellidoPaterno = destinatarioApellidoPaterno; }

    public String getDestinatarioApellidoMaterno() { return destinatarioApellidoMaterno; }
    public void setDestinatarioApellidoMaterno(String destinatarioApellidoMaterno) { this.destinatarioApellidoMaterno = destinatarioApellidoMaterno; }

    public String getDestinatarioCelular() { return destinatarioCelular; }
    public void setDestinatarioCelular(String destinatarioCelular) { this.destinatarioCelular = destinatarioCelular; }

    public String getTipoPaquete() { return tipoPaquete; }
    public void setTipoPaquete(String tipoPaquete) { this.tipoPaquete = tipoPaquete; }
}
