package com.example.turnosrotativos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jornada-laboral")
public class JornadaLaboralController {

    @Autowired
    private JornadaLaboralService jornadaLaboralService;

    @GetMapping
    public ResponseEntity<List<JornadaLaboral>> getAllJornadasLaborales() {
        List<JornadaLaboral> jornadasLaborales = jornadaLaboralService.getAllJornadasLaborales();
        return new ResponseEntity<>(jornadasLaborales, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JornadaLaboral> getJornadaLaboralById(@PathVariable Long id) {
        JornadaLaboral jornadaLaboral = jornadaLaboralService.getJornadaLaboralById(id);
        return new ResponseEntity<>(jornadaLaboral, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<JornadaLaboral> createJornadaLaboral(@RequestBody JornadaLaboral jornadaLaboral) {
        JornadaLaboral nuevaJornadaLaboral = jornadaLaboralService.createJornadaLaboral(jornadaLaboral);
        return new ResponseEntity<>(nuevaJornadaLaboral, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JornadaLaboral> updateJornadaLaboral(@PathVariable Long id, @RequestBody JornadaLaboral jornadaLaboral) {
        JornadaLaboral updatedJornadaLaboral = jornadaLaboralService.updateJornadaLaboral(id, jornadaLaboral);
        return new ResponseEntity<>(updatedJornadaLaboral, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJornadaLaboral(@PathVariable Long id) {
        jornadaLaboralService.deleteJornadaLaboral(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}