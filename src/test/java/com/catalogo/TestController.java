package com.catalogo;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class TestController {


    @Autowired
    private  MockMvc mockMvc;

    @Test
    @WithAnonymousUser
    public void listaAutoriWhenUtenteIsAnonymous() throws Exception {
        this.mockMvc.perform(get("/api/autore/")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    public void listaCategorieWhenUtenteIsAnonymous() throws Exception {
        this.mockMvc.perform(get("/api/categoria/")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    public void listaLibriWhenUtenteIsAnonymous() throws Exception {
        this.mockMvc.perform(get("/api/libro/")).andExpect(status().isUnauthorized());
    }
    
    @Test
    @WithMockUser(username = "user", password = "user")
    public void listaLibriWhenUtenteIsUser() throws Exception {
        this.mockMvc.perform(get("/api/libro/")).andExpect(status().isOk());
    }
    
    @Test
    @WithMockUser(username = "user", password = "user")
    public void deleteLibroWhenUtenteIsUser() throws Exception {
        this.mockMvc.perform(delete("/api/libro/1")).andExpect(status().isForbidden());
    }
    

}
