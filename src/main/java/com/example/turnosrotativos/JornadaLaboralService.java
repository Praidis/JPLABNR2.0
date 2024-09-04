package com.example.turnosrotativos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JornadaLaboralService {

    @Autowired
    private JornadaLaboralRepository jornadaLaboralRepository;

    public List<JornadaLaboral> getAllJornadasLaborales() {
        return jornadaLaboralRepository.findAll();
    }

    public JornadaLaboral getJornadaLaboralById(Long id) {
        return jornadaLaboralRepository.findById(id).orElseThrow();
    }

    public JornadaLaboral createJornadaLaboral(JornadaLaboral jornadaLaboral) {
        return jornadaLaboralRepository.save(jornadaLaboral);
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
