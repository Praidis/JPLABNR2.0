package com.example.turnosrotativos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConceptoLaboralService {

    @Autowired
    private ConceptoLaboralRepository conceptoLaboralRepository;

    @Autowired
    private JornadaLaboralRepository jornadaLaboralRepository;

    public List<ConceptoLaboral> getAllConceptosLaborales() {
        return conceptoLaboralRepository.findAll();
    }

    public ConceptoLaboral getConceptoLaboralById(Long id) {
        return conceptoLaboralRepository.findById(id).orElseThrow();
    }

    public ConceptoLaboral createConceptoLaboral(ConceptoLaboral conceptoLaboral) {
        if (conceptoLaboral.getHsMaximo() == null) {
            throw new IllegalArgumentException("La hora máxima no puede ser nula.");
        }
        return conceptoLaboralRepository.save(conceptoLaboral);
    }

    public ConceptoLaboral updateConceptoLaboral(Long id, ConceptoLaboral conceptoLaboral) {
        if (conceptoLaboral.getHsMaximo() == null) {
            throw new IllegalArgumentException("La hora máxima no puede ser nula.");
        }
        ConceptoLaboral existingConceptoLaboral = getConceptoLaboralById(id);
        existingConceptoLaboral.setNombre(conceptoLaboral.getNombre());
        existingConceptoLaboral.setHsMaximo(conceptoLaboral.getHsMaximo());
        existingConceptoLaboral.setHsMinimo(conceptoLaboral.getHsMinimo());
        existingConceptoLaboral.setLaborable(conceptoLaboral.getLaborable());
        return conceptoLaboralRepository.save(existingConceptoLaboral);
    }

    public void deleteConceptoLaboral(Long id) {
        ConceptoLaboral conceptoLaboral = conceptoLaboralRepository.findById(id).orElseThrow(() -> new ConflictException("Concepto laboral no encontrado"));

        // Crear un ConceptoLaboral válido
        ConceptoLaboral otroConceptoLaboralValido = new ConceptoLaboral();
        otroConceptoLaboralValido.setNombre("Nuevo concepto laboral");
        otroConceptoLaboralValido = conceptoLaboralRepository.save(otroConceptoLaboralValido);

        // Actualizar las JornadaLaboral relacionadas con el ConceptoLaboral a borrar con el ConceptoLaboral válido
        List<JornadaLaboral> jornadasLaborales = jornadaLaboralRepository.findByConceptoLaboral(conceptoLaboral);
        for (JornadaLaboral jornadaLaboral : jornadasLaborales) {
            jornadaLaboral.setConceptoLaboral(otroConceptoLaboralValido);
            jornadaLaboralRepository.save(jornadaLaboral);
        }

        conceptoLaboralRepository.deleteById(id);
    }
}
