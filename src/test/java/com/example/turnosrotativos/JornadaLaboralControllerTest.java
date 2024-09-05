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
public class JornadaLaboralControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JornadaLaboralService jornadaLaboralService;

    @Test
    public void getAllJornadasLaborales() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/jornada-laboral"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getJornadaLaboralById() throws Exception {
        JornadaLaboral jornadaLaboral = jornadaLaboralService.getJornadaLaboralById(1L);
        mockMvc.perform(MockMvcRequestBuilders.get("/jornada-laboral/" + jornadaLaboral.getIdJornada()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void createJornadaLaboral() throws Exception {
        String jornadaLaboralJson = "{\"empleado\":{\"id\":1}," +
                "\"conceptoLaboral\":{\"id\":1}," +
                "\"fecha\":\"2022-01-01\"," +
                "\"hsTrabajadas\":8}";

        mockMvc.perform(MockMvcRequestBuilders.post("/jornada-laboral/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jornadaLaboralJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    public void deleteJornadaLaboral() throws Exception {
        JornadaLaboral jornadaLaboral = jornadaLaboralService.getJornadaLaboralById(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/jornada-laboral/" + jornadaLaboral.getIdJornada()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}