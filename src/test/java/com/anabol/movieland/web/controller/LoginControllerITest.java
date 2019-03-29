package com.anabol.movieland.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/action-servlet.xml",
        "file:src/main/webapp/WEB-INF/applicationContext.xml"})
@WebAppConfiguration
public class LoginControllerITest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testLogin() throws Exception {
        mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\"email\" : \"darlene.edwards15@example.com\",\"password\" : \"bricks\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.nickname", is("Дарлин Эдвардс")))
                .andExpect(jsonPath("$.uuid").isNotEmpty());
    }

    @Test
    public void testLoginFail() throws Exception {
        mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\"email\" : \"darlene.edwards15@example.com\",\"password\" : \"WRONG_PASSWORD\"}"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testLogoutDummy() throws Exception {
        mockMvc.perform(delete("/logout").header("uuid", "some fake uuid"))
                .andExpect(status().isOk());
    }

    @Test
    public void testLogoutEmptyUuid() throws Exception {
        mockMvc.perform(delete("/logout"))
                .andExpect(status().isBadRequest());
    }
}
