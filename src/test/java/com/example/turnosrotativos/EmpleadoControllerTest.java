package com.example.turnosrotativos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
public class EmpleadoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Test
    public void getAllEmpleados() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/empleado"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getEmpleado() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/empleado/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void createEmpleado() throws Exception {
        String empleadoJson = "{\"nombre\":\"Lionel\",\"apellido\":\"Messi\",\"documento\":\"30567589\",\"email\":\"lmessi01@example.com\",\"fechaNacimiento\":\"1987-06-24\",\"fechaIngreso\":\"2022-01-01\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/empleado/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(empleadoJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void updateEmpleado() throws Exception {

        Empleado empleado = new Empleado();
        empleado.setNombre("Jose");
        empleado.setApellido("Sambrano");
        empleado.setDocumento("20302758");
        empleado.setEmail("jose.sambrano@example.com");
        empleado.setFechaNacimiento(LocalDate.of(1990, 1, 1));
        empleado.setFechaIngreso(LocalDate.of(2012, 1, 1));


        // Persist the employee to generate the ID
        empleadoRepository.save(empleado);

        String empleadoJson = "{\"nombre\":\"Jose\",\"apellido\":\"Sambrano\",\"documento\":\"20302758\",\"email\":\"jose.sambrano@example.com\",\"fechaNacimiento\":\"1990-01-01\",\"fechaIngreso\":\"2022-01-01\"}";
        mockMvc.perform(MockMvcRequestBuilders.put("/empleado/" + empleado.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(empleadoJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void deleteEmpleado() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/empleado/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}