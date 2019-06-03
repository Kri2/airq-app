package io.github.kri2.airq.airqapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * does the same as AirqAppApplicationTests (which is context loading) and extra checks if controller was loaded
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTest
{
    @Autowired//--->The @Autowired annotation is interpreted by the Spring and the controller is injected before the test methods are run.
    WelcomeController welcomeController;
    @Test
    public void contextLoads() throws Exception{
        assertNotNull(welcomeController);
    }
}
