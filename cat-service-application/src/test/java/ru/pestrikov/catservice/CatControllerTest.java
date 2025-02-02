package ru.pestrikov.catservice;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.pestrikov.catservice.CustomUserSetup.CatOwnerSetup;
import ru.pestrikov.catservice.CustomUserSetup.WithMockCustomUser;
import ru.pestrikov.catservice.domain.in.application.CatService;
import ru.pestrikov.catservice.domain.model.Cat;
import ru.pestrikov.catservice.domain.model.Owner;
import ru.pestrikov.catservice.in.rest.CatController;
import ru.pestrikov.catservice.mapper.EntityMapper;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(CatController.class)
@Import({MapperConfiguration.class})
public class CatControllerTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    EntityMapper entityMapper;

    private final Owner owner = CatOwnerSetup.owner;
    private final Cat cat = CatOwnerSetup.cat;

    @MockBean
    CatService catService;

    @Test
    @WithMockCustomUser
    public void whenGetCat_thenCorrect() throws Exception {
        when(catService.findById(cat.getId())).thenReturn(cat);
        mvc.perform(get("/api/v1/cats/" + cat.getId()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockCustomUser
    public void whenCreateCat_thenCorrect() throws Exception {
        when(catService.create(entityMapper.fromCatRequest(entityMapper.toCatRequest(cat)))).thenReturn(cat);
        mvc.perform(post("/api/v1/cats")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entityMapper.toCatRequest(cat))))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockCustomUser
    public void whenPatchCat_thenCorrect() throws Exception {
        when(catService.update(cat.getId(), entityMapper.fromCatRequest(entityMapper.toCatRequest(cat)))).thenReturn(cat);
        mvc.perform(patch("/api/v1/cats/" + cat.getId())
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entityMapper.toCatRequest(cat))))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockCustomUser
    public void whenDeleteCat_thenCorrect() throws Exception {
        when(catService.create(entityMapper.fromCatRequest(entityMapper.toCatRequest(cat)))).thenReturn(cat);
        mvc.perform(delete("/api/v1/cats/" + cat.getId()).with(csrf())).andExpect(status().isOk());
    }
}
