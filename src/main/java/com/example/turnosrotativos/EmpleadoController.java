package com.example.turnosrotativos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<List<Empleado>> getAllEmpleados() {
        List<Empleado> empleados = empleadoService.getAllEmpleados();
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

    @GetMapping("/{idEmpleado}")
    public ResponseEntity<Empleado> getEmpleado(@PathVariable Long idEmpleado) {
        try {
            Empleado empleado = empleadoService.getEmpleadoById(idEmpleado);
            return new ResponseEntity<>(empleado, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/new")
    public ResponseEntity<?> crearEmpleado(@RequestBody Empleado empleado) {
        try {
            Empleado nuevoEmpleado = empleadoService.crearEmpleado(empleado);
            return new ResponseEntity<>(nuevoEmpleado, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ConflictException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{idEmpleado}")
    public ResponseEntity<?> updateEmpleado(@PathVariable Long idEmpleado, @RequestBody Empleado empleado) {
        try {
            Empleado updatedEmpleado = empleadoService.updateEmpleado(idEmpleado, empleado);
            return new ResponseEntity<>(updatedEmpleado, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{idEmpleado}")
    public ResponseEntity<?> deleteEmpleado(@PathVariable Long idEmpleado) {
        try {
            empleadoService.eliminarEmpleado(idEmpleado);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}