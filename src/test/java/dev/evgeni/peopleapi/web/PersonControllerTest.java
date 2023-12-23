package dev.evgeni.peopleapi.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import dev.evgeni.peopleapi.error.InvalidObjectException;
import dev.evgeni.peopleapi.mapper.PersonMapper;
import dev.evgeni.peopleapi.model.Person;
import dev.evgeni.peopleapi.repository.FilmRepository;
import dev.evgeni.peopleapi.repository.PersonPagingRepository;
import dev.evgeni.peopleapi.repository.PersonRepository;
import dev.evgeni.peopleapi.repository.PhotoRepository;
import dev.evgeni.peopleapi.service.ObjectValidator;

@WebMvcTest(controllers = PersonController.class,
                excludeAutoConfiguration = SecurityAutoConfiguration.class,
                excludeFilters = @Filter(
                                type = org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE,
                                classes = JWTLoginFilter.class))
// @Profile("unit")
public class PersonControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private PersonMapper personMapper;

        @MockBean
        private ObjectValidator validator;

        @MockBean
        private PersonRepository personRepository;

        @MockBean
        private PersonPagingRepository personPagingRepository;

        @MockBean
        private PhotoRepository photoRepository;

        @MockBean
        private FilmRepository filmRepository;

        @InjectMocks
        private PersonController controller;

        @Test
        void shouldHavePaginationOnListing() throws Exception {

                Page<Person> emptyPersonPage =
                                new PageImpl<>(Collections.emptyList(), PageRequest.of(0, 10), 0);
                when(personPagingRepository.findAll(PageRequest.of(0, 10)))
                                .thenReturn(emptyPersonPage);

                MvcResult result = mockMvc
                                .perform(get("/person").contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.metadata.currPage").value(0)).andReturn();

                verify(personPagingRepository, times(1)).findAll(PageRequest.of(0, 10));

                String resultBody = result.getResponse().getContentAsString();

                System.out.println(resultBody);
        }

        @Test
        void shouldReturnBadRequestWithErrors() throws Exception {

                Map<String, String> constraintViolations = new HashMap<>();
                constraintViolations.put("age", "cannot be negative");
                doThrow(new InvalidObjectException("DTO Invalid", constraintViolations))
                                .when(validator).validate(any());


                MvcResult result = mockMvc
                                .perform(post("/person").contentType(MediaType.APPLICATION_JSON)
                                                .content("""
                                                                   {
                                                                        "firstName": "Ivan",
                                                                        "age": -100
                                                                   }
                                                                """))
                                .andExpect(status().isBadRequest())
                                .andExpect(jsonPath("$.errors.age").value("cannot be negative"))
                                .andReturn();

                System.out.println(result.getResponse().getContentAsString());
        }


}
