package mindata.delmoralcristian.superhero.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mindata.delmoralcristian.superhero.SuperHeroApplication;
import mindata.delmoralcristian.superhero.dto.SuperHeroRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuperHeroApplication.class)
public class SuperHeroManagementControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getAllSuperheroesOK() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                            .get("/api/superhero")
                            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").isNotEmpty());
    }

    @Test
    public void getSuperheroByIdOK() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/superhero/id/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    public void getSuperheroByIdNotFound() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/superhero/id/{id}", 78)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$").doesNotExist());
    }

    @Test
    public void getSuperheroByNameOk() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/superhero/name/{name}", "atm")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").isNotEmpty());
    }

    @Test
    public void getSuperheroByNameNotFound() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/superhero/name/{name}", "someword")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*]").isEmpty());
    }

    @Test
    public void updateSuperheroOK() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/superhero/id/{id}", 3)
                        .content(asJsonString(SuperHeroRequest.builder().name("SuperXXX").health(80).build()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("SuperXXX"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.health").value(80));
    }

    @Test
    public void updateSuperheroNotFound() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/superhero/id/{id}", 78)
                        .content(asJsonString(SuperHeroRequest.builder().name("SuperXXX").health(80).build()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$").doesNotExist());
    }

    @Test
    public void deleteSuperheroAccepted() throws Exception
    {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/superhero/id/{id}", 1) )
                .andExpect(status().isAccepted());
    }

    @Test
    public void deleteSuperheroNotFound() throws Exception
    {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/superhero/id/{id}", 78) )
                .andExpect(status().isNotFound());
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
