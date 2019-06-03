package io.github.kri2.airq.airqapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
//@WebMvcTest(WelcomeController.class)

public class HttpRequestTest2
{
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    MockHttpSession session;
    @MockBean
    GiosApiService giosApiService;
    //Mockito.when()
    //zrobić taki mock udający giosApiService (given/when/then technique)??
    //when(giosApiService.loadDataFrom)
    
    //coś tu jest nie tak
//    @Autowired
//    GiosApiService giosApiService;
//    when(giosApiService.loadDataFromGiosServer()).thenReturn(new HashMap<String,String>);
    
    @Test
    public void testDefaultResponse() throws Exception{
        // prepare data and mock's behaviour
        // not much to do here, just get standard response
    /*
        MvcResult result = mockMvc
            .perform(get("/")
                                           .contentType(MediaType.TEXT_HTML)
                                           .accept(MediaType.TEXT_HTML)
                    );*/
    /*
        this.mockMvc.perform(get("/").session(session).accept(MediaType.TEXT_HTML))
                    .andExpect(status().isOk())
                    .andExpect(view().name("welcome"));*/
    }
}
