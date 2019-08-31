package io.github.kri2.airq.airqapp;

import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ParamsMap
{
    public static final Map<String,String> urls = new HashMap<String,String>(){{
        put("pm25", "http://api.gios.gov.pl/pjp-api/rest/data/getData/2772");
        put("pm10", "http://api.gios.gov.pl/pjp-api/rest/data/getData/2770");
    }};
}
