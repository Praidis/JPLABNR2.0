package com.example.turnosrotativos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Empleado crearEmpleado(Empleado empleado) {
        validarEmpleado(empleado);
        return empleadoRepository.save(empleado);
    }

    @Transactional
    public Empleado updateEmpleado(Long idEmpleado, Empleado empleado) {
        Empleado existingEmpleado = empleadoRepository.findById(idEmpleado).orElseThrow(() ->
                new NoSuchElementException("No se encontró el empleado con Id: " + idEmpleado));

        if (!existingEmpleado.getDocumento().equals(empleado.getDocumento()) &&
                empleadoRepository.findByDocumento(empleado.getDocumento()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un empleado con el documento ingresado.");
        }

        existingEmpleado.setNombre(empleado.getNombre());
        existingEmpleado.setApellido(empleado.getApellido());
        existingEmpleado.setEmail(empleado.getEmail());
        existingEmpleado.setFechaNacimiento(empleado.getFechaNacimiento());
        existingEmpleado.setFechaIngreso(empleado.getFechaIngreso());

        return empleadoRepository.save(existingEmpleado);
    }

    @Transactional
    public void eliminarEmpleado(Long idEmpleado) {
        empleadoRepository.deleteById(idEmpleado);
    }

    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    public Empleado getEmpleadoById(Long idEmpleado) {
        return empleadoRepository.findById(idEmpleado).orElseThrow(() ->
                new NoSuchElementException("No se encontró el empleado con Id: " + idEmpleado));
    }

    private void validarEmpleado(Empleado empleado) {
        if (empleado.getFechaNacimiento().isAfter(LocalDate.now().minusYears(18))) {
            throw new IllegalArgumentException("La edad del empleado no puede ser menor a 18 años.");
        }

        if (empleado.getId() == null) { // if it's a new employee
            if (empleadoRepository.findByDocumento(empleado.getDocumento()).isPresent()) {
                throw new IllegalArgumentException("Ya existe un empleado con el documento ingresado.");
            }
            if (empleadoRepository.findByEmail(empleado.getEmail()).isPresent()) {
                throw new IllegalArgumentException("Ya existe un empleado con el email ingresado.");
            }
        }

        if (empleado.getFechaIngreso().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de ingreso no puede ser posterior al día de la fecha.");
        }

        if (empleado.getFechaNacimiento().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser posterior al día de la fecha.");
        }

        validateEmail(empleado.getEmail());
        validateNameOrApellido(empleado.getNombre());
        validateNameOrApellido(empleado.getApellido());
        validateRequiredFields(empleado);
    }

    private void validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        Matcher mat = pat.matcher(email);
        if (!mat.find()) {
            throw new IllegalArgumentException("El email ingresado no es válido.");
        }
    }

    private void validateNameOrApellido(String name) {
        String nameRegex = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$";
        Pattern pat = Pattern.compile(nameRegex);
        Matcher mat = pat.matcher(name);
        if (!mat.find()) {
            throw new IllegalArgumentException("El nombre o apellido ingresado no es válido.");
        }
    }

    private void validateRequiredFields(Empleado empleado) {
        if (empleado.getNombre() == null || empleado.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es un campo obligatorio.");
        }
        if (empleado.getApellido() == null || empleado.getApellido().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido es un campo obligatorio.");
        }
        if (empleado.getDocumento() == null || empleado.getDocumento().trim().isEmpty()) {
            throw new IllegalArgumentException("El documento es un campo obligatorio.");
        }
        if (empleado.getEmail() == null || empleado.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("El email es un campo obligatorio.");
        }
        if (empleado.getFechaNacimiento() == null) {
            throw new IllegalArgumentException("La fecha de nacimiento es un campo obligatorio.");
        }
        if (empleado.getFechaIngreso() == null) {
            throw new IllegalArgumentException("La fecha de ingreso es un campo obligatorio.");
        }
    }
}
