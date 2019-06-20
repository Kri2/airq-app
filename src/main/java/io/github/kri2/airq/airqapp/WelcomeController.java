package io.github.kri2.airq.airqapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
public class WelcomeController
{
    Logger LOGGER = LoggerFactory.getLogger(WelcomeController.class);
    
    @Autowired
    GiosApiService giosApiService;
    
    Map<String,String> urls = new HashMap<String,String>(){{
        put("pm25","http://api.gios.gov.pl/pjp-api/rest/data/getData/2772");
        put("pm10", "http://api.gios.gov.pl/pjp-api/rest/data/getData/2770");
    }};
    
    @RequestMapping("/")
    public String welcome(Map<String, Object> model){
        model.put("message", "Welcome to airq app!");
        Map<String, String> map = giosApiService.loadDataFromGiosServer();
        Set<ParameterReadout> set = giosApiService.loadToSet();
        set.stream().forEach(x->System.out.println(x.getKey()+x.getValue()));
        model.put("VALUES",set);
        //set.stream().forEach(p->model.put(p.getKey(),p.getValue()));
        //model.put("PM2",  map.get("PM2.5"));
        //model.put("PM10", map.get("PM10"));
        return "welcome";
    }
}
