package io.github.kri2.airq.airqapp;

import io.github.kri2.airq.airqapp.service.GiosApiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Future Integration test
 */
@SpringBootTest  // full app context is needed
//@WebMvcTest(WelcomeController.class) // This is integration test, full context needed
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class HttpRequestTest
{
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    MockHttpSession session;
    @MockBean
    GiosApiService giosApiService;
    
    @Test
    public void testDefaultResponse() throws Exception{
        //given
        // prepare data and mock's behaviour
        // not much to do here, just get standard response
        //when
        mockMvc.perform(get("/").accept(MediaType.TEXT_HTML))
               .andExpect(status().isOk())
               .andExpect(content().string(containsString("Welcome to airq app!")));
        //then
    }
}
