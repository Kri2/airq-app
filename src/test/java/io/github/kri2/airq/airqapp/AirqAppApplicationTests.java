package io.github.kri2.airq.airqapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest //--->tells Spring Boot to go and look for a main configuration class (one with @SpringBootApplication for instance), and use that to start a Spring application context.
public class AirqAppApplicationTests {

	//---> The first thing you can do is write a simple sanity check test that will fail if the application context cannot start.
	@Test
	public void contextLoads() throws Exception{
	}

}

