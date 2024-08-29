package com.example.turnosrotativos;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.NoSuchElementException;

public class EmpleadoController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping("/{idEmpleado}")
    public ResponseEntity<Empleado> getEmpleado(@PathVariable Long idEmpleado) {
        try {
            Empleado empleado = empleadoRepository.findById(idEmpleado).orElseThrow();
            return new ResponseEntity<>(empleado, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idEmpleado}")
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable Long idEmpleado, @RequestBody Empleado empleado) {
        Empleado existingEmpleado = empleadoRepository.findById(idEmpleado).orElse(null);
        if (existingEmpleado != null) {
            existingEmpleado.setNombre(empleado.getNombre());
            existingEmpleado.setApellido(empleado.getApellido());
            existingEmpleado.setDocumento(empleado.getDocumento());
            existingEmpleado.setEmail(empleado.getEmail());
            Empleado updatedEmpleado = empleadoRepository.save(existingEmpleado);
            return new ResponseEntity<>(updatedEmpleado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
