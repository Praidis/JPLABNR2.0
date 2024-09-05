package com.example.turnosrotativos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class EmpleadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllEmpleados() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/empleado"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getEmpleadoById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/empleado/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void createEmpleado() throws Exception {
        String empleadoJson = "{\"nombre\":\"John\",\"apellido\":\"Doe\",\"documento\":\"12345678\",\"email\":\"john.doe@example.com\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/empleado")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(empleadoJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void updateEmpleado() throws Exception {
        Empleado empleado = new Empleado(); // Inicializa el objeto Empleado con los datos necesarios
        String empleadoJson = "{\"nombre\":\"Jane\",\"apellido\":\"Smith\",\"documento\":\"98765432\",\"email\":\"jane.smith@example.com\"}";
        mockMvc.perform(MockMvcRequestBuilders.put("/empleado/1", empleado.getId())
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