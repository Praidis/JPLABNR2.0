package com.example.turnosrotativos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JornadaLaboralService {

    private final JornadaLaboralRepository jornadaLaboralRepository;
    private final ConceptoLaboralRepository conceptoLaboralRepository;

    @Autowired
    public JornadaLaboralService(JornadaLaboralRepository jornadaLaboralRepository, ConceptoLaboralRepository conceptoLaboralRepository) {
        this.jornadaLaboralRepository = jornadaLaboralRepository;
        this.conceptoLaboralRepository = conceptoLaboralRepository;
    }

    public List<JornadaLaboral> getAllJornadasLaborales() {
        return jornadaLaboralRepository.findAll();
    }

    public JornadaLaboral getJornadaLaboralById(Long id) {
        return jornadaLaboralRepository.findById(id).orElseThrow();
    }

    public JornadaLaboral createJornadaLaboral(JornadaLaboral jornadaLaboral) {
        try {
            // Verify that the conceptoLaboral exists
            ConceptoLaboral conceptoLaboral = conceptoLaboralRepository.findById(jornadaLaboral.getConceptoLaboral().getIdConcepto()).orElseThrow();
            jornadaLaboral.setConceptoLaboral(conceptoLaboral);

            JornadaLaboral nuevaJornadaLaboral = jornadaLaboralRepository.save(jornadaLaboral);
            System.out.println("Jornada laboral creada con éxito: " + nuevaJornadaLaboral);
            return nuevaJornadaLaboral;
        } catch (Exception e) {
            System.out.println("Error al crear jornada laboral: " + e.getMessage());
            return null;
        }
    }

    public JornadaLaboral updateJornadaLaboral(Long id, JornadaLaboral jornadaLaboral) {
        JornadaLaboral existingJornadaLaboral = getJornadaLaboralById(id);
        existingJornadaLaboral.setEmpleado(jornadaLaboral.getEmpleado());
        existingJornadaLaboral.setConceptoLaboral(jornadaLaboral.getConceptoLaboral());
        existingJornadaLaboral.setFecha(jornadaLaboral.getFecha());
        existingJornadaLaboral.setHsTrabajadas(jornadaLaboral.getHsTrabajadas());
        return jornadaLaboralRepository.save(existingJornadaLaboral);
    }

    public void deleteJornadaLaboral(Long id) {
        jornadaLaboralRepository.deleteById(id);
    }
}
