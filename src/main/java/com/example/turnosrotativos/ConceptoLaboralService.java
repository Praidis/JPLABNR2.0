package com.example.turnosrotativos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConceptoLaboralService {

    @Autowired
    private ConceptoLaboralRepository conceptoLaboralRepository;

    public List<ConceptoLaboral> getAllConceptosLaborales() {
        return conceptoLaboralRepository.findAll();
    }

    public ConceptoLaboral getConceptoLaboralById(Long id) {
        return conceptoLaboralRepository.findById(id).orElseThrow();
    }

    public ConceptoLaboral createConceptoLaboral(ConceptoLaboral conceptoLaboral) {
        return conceptoLaboralRepository.save(conceptoLaboral);
    }

    public ConceptoLaboral updateConceptoLaboral(Long id, ConceptoLaboral conceptoLaboral) {
        ConceptoLaboral existingConceptoLaboral = getConceptoLaboralById(id);
        existingConceptoLaboral.setNombre(conceptoLaboral.getNombre());
        existingConceptoLaboral.setHsMaximo(conceptoLaboral.getHsMaximo());
        existingConceptoLaboral.setHsMinimo(conceptoLaboral.getHsMinimo());
        existingConceptoLaboral.setLaborable(conceptoLaboral.getLaborable());
        return conceptoLaboralRepository.save(existingConceptoLaboral);
    }

    public void deleteConceptoLaboral(Long id) {
        conceptoLaboralRepository.deleteById(id);
    }
}
