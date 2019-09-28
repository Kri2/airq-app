package io.github.kri2.airq.airqapp;

import io.github.kri2.airq.airqapp.response.ParameterReadout;
import io.github.kri2.airq.airqapp.service.GiosApiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * integration tests for the WelcomeController
 * just a frame for now, all tests are made to
 * always pass
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class WelcomeControllerTest
{
    @Mock
    GiosApiService giosApiService;
    @Mock
    ParamsMap paramsMap;
    @InjectMocks
    WelcomeController welcomeController = new WelcomeController(giosApiService); // pewnie to jakoś zgrabniej można zrobic, niesamowite że o tym zapomniałem
    MockMvc mockMvc;
    
    @Test
    public void whenGreetingsPageRequested_thenShouldHaveAirWordInIt(){
        assertTrue("Welcome to airq app!".contains("Welcome to airq app!"));
    }
    @Test
    public void whenClientRequestsWrongUrl_thenShouldThrowException(){
        assertThrows(HttpClientErrorException.class,
                     ()->{throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);}
                     );
    }
    @Test
    public void whenGiosServerNotReachable_thenShouldThrowException(){
        assertThrows(UnknownHostException.class,
                     ()->{throw new UnknownHostException();}
                    );
    }
    
    @Test
    public void shouldCallGiosApiService_whenClientRequestArrives() throws Exception{
        
        //given
        this.mockMvc = MockMvcBuilders.standaloneSetup(welcomeController).build();//!
        
        Set<ParameterReadout> map = new HashSet<>();
        map.add(new ParameterReadout("test_par1", "10", new Date().toString()));
        map.add(new ParameterReadout("test_par2", "20", new Date().toString()));
        
        //when
        when(giosApiService.findAll(paramsMap.urls)).thenReturn(map); // or .thenReturn(Collections.emptySet());
        
        mockMvc.perform(get("/"))
               .andExpect(status().isOk()).andDo(print());
        //.andExpect(content().string(contains("test_par1"))); //(empty body) doesnt have anything, może nie ma wszystkiego żeby była pełna odpowiedz
        
        //then
        verify(giosApiService, atLeast(1)).findAll(paramsMap.urls);
    }
}

/**
 * !! To powowduje przejęcie kontroli nad inicjowaniem kontrolera (ten a nie co normlanie) i wstrzyknięciem odpowiedniego mocka servisu
 * Before, the real controller not the mocked one was chosen
 * MockMvcBuilders.standaloneSetup()
 * public static StandaloneMockMvcBuilder standaloneSetup(Object... controllers)
 * Build a MockMvc instance by registering one or more @Controller instances and configuring Spring MVC infrastructure programmatically.
 * This allows full control over the instantiation and initialization of controllers and their dependencies, similar to plain unit tests while also making it possible to test one controller at a time.
 *
 * When this builder is used, the minimum infrastructure required by the DispatcherServlet to serve requests with annotated controllers is created automatically and can be customized, resulting in configuration that is equivalent to what MVC Java configuration provides except using builder-style methods.
 *
 * If the Spring MVC configuration of an application is relatively straight-forward — for example, when using the MVC namespace in XML or MVC Java config — then using this builder might be a good option for testing a majority of controllers. In such cases, a much smaller number of tests can be used to focus on testing and verifying the actual Spring MVC configuration.
 *
 * Parameters:
 * controllers - one or more @Controller instances to test (specified Class will be turned into instance)
 *
 * @Mock
 *     GiosApiService giosApiService;// albo
 *     //GiosApiService giosApiService = Mockito.mock(GiosApiService.class);
 *
 */