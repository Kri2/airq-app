package io.github.kri2.airq.airqapp;


import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * we should also write some tests that assert the behaviour of our application. To do that we could start the application up and listen for a connection like it would do in production, and then send an HTTP request and assert the response.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class HttpRequestTest
{
    @LocalServerPort
    private int port;
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Autowired
    private WebApplicationContext wac;
    
    private MockMvc mockMvc;
    
    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception{
        // check that contains default message
        /*
        assertThat(this.restTemplate.getForObject(
            "http://localhost:"+port+"/",String.class)
                                    .contains("Hello World");*/
        /*
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        mockMvc.perform(get("http://localhost:"+port+"/"))
               .andExpect(status().isOk());
        */
        
        //---> probably to refactor, but so far the best I came up with
        assertTrue(
            this.restTemplate.getForObject("http://localhost:"+port+"/",String.class)
                             .contains("Air")
                  );
        
    }
}
