package com.example.turnosrotativos;

import com.example.turnosrotativos.ConceptoLaboral;
import jakarta.persistence.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "jornadas_laborales")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class JornadaLaboral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJornada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmpleado", nullable = false)
    private Empleado empleado;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST) // or CascadeType.ALL
    @JoinColumn(name = "idConcepto", nullable = false)
    private ConceptoLaboral conceptoLaboral;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private Integer hsTrabajadas;

    public Long getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(Long idJornada) {
        this.idJornada = idJornada;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public ConceptoLaboral getConceptoLaboral() {
        return conceptoLaboral;
    }

    public void setConceptoLaboral(ConceptoLaboral conceptoLaboral) {
        this.conceptoLaboral = conceptoLaboral;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getHsTrabajadas() {
        return hsTrabajadas;
    }

    public void setHsTrabajadas(Integer hsTrabajadas) {
        this.hsTrabajadas = hsTrabajadas;
    }
}
