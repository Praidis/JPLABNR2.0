package com.example.turnosrotativos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/concepto-laboral")
public class ConceptoLaboralController {

    @Autowired
    private ConceptoLaboralService conceptoLaboralService;

    @GetMapping
    public ResponseEntity<List<ConceptoLaboral>> getAllConceptosLaborales() {
        List<ConceptoLaboral> conceptosLaborales = conceptoLaboralService.getAllConceptosLaborales();
        return new ResponseEntity<>(conceptosLaborales, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConceptoLaboral> getConceptoLaboralById(@PathVariable Long id) {
        ConceptoLaboral conceptoLaboral = conceptoLaboralService.getConceptoLaboralById(id);
        return new ResponseEntity<>(conceptoLaboral, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ConceptoLaboral> createConceptoLaboral(@RequestBody ConceptoLaboral conceptoLaboral) {
        ConceptoLaboral nuevaConceptoLaboral = conceptoLaboralService.createConceptoLaboral(conceptoLaboral);
        return new ResponseEntity<>(nuevaConceptoLaboral, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConceptoLaboral> updateConceptoLaboral(@PathVariable Long id, @RequestBody ConceptoLaboral conceptoLaboral) {
        ConceptoLaboral updatedConceptoLaboral = conceptoLaboralService.updateConceptoLaboral(id, conceptoLaboral);
        return new ResponseEntity<>(updatedConceptoLaboral, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConceptoLaboral(@PathVariable Long id) {
        conceptoLaboralService.deleteConceptoLaboral(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
