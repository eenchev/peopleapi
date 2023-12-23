package dev.evgeni.peopleapi.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("integration")
// @Profile("integration")
public class PersonEndpointsTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "Pesho", roles = {"ADMIN"})
    void shouldAddAValidPerson() throws Exception {
        mockMvc.perform(
                post("/person").header(HttpHeaders.CONTENT_TYPE, "application/json").content("""
                            {
                                "firstName": "Ivan",
                                "age": 14,
                                "egn": 1111111111
                            }
                        """)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("Ivan"))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    @WithMockUser(username = "ivan", roles = {"ADMIN"})
    void shouldThrowValidationErrorForInvalidPerson() throws Exception {
        mockMvc.perform(
                post("/person").header(HttpHeaders.CONTENT_TYPE, "application/json").content("""
                            {
                                "firstName": "Ivan",
                                "age": -14,
                                "egn": 22
                            }
                        """)).andExpect(status().isBadRequest());
    }

}
