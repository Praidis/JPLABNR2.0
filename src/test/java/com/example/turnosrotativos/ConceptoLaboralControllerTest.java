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
public class ConceptoLaboralControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllConceptosLaborales() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/concepto-laboral"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getConceptoLaboralById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/concepto-laboral/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void createConceptoLaboral() throws Exception {
        String conceptoLaboralJson = "{\"nombre\":\"Turno Normal\",\"hsMaximo\":8,\"hsMinimo\":6,\"laborable\":true}";
        mockMvc.perform(MockMvcRequestBuilders.post("/concepto-laboral")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(conceptoLaboralJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void updateConceptoLaboral() throws Exception {
        String conceptoLaboralJson = "{\"nombre\":\"Turno Extra\",\"hsMaximo\":10,\"hsMinimo\":8,\"laborable\":true}";
        mockMvc.perform(MockMvcRequestBuilders.put("/concepto-laboral/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(conceptoLaboralJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

}
