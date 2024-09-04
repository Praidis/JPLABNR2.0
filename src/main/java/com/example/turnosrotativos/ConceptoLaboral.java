package com.example.turnosrotativos;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "conceptos_laborales")
public class ConceptoLaboral implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_concepto")
    private Long idConcepto;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false)
    private Integer hsMaximo;

    @Column(nullable = false)
    private Integer hsMinimo;

    @Column(nullable = false)
    private Boolean laborable;

    // Getters and Setters
    public Long getIdConcepto() {
        return idConcepto;
    }

    public void setIdConcepto(Long idConcepto) {
        this.idConcepto = idConcepto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getHsMaximo() {
        return hsMaximo;
    }

    public void setHsMaximo(Integer hsMaximo) {
        this.hsMaximo = hsMaximo;
    }

    public Integer getHsMinimo() {
        return hsMinimo;
    }

    public void setHsMinimo(Integer hsMinimo) {
        this.hsMinimo = hsMinimo;
    }

    public Boolean getLaborable() {
        return laborable;
    }

    public void setLaborable(Boolean laborable) {
        this.laborable = laborable;
    }
}
