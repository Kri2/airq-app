package io.github.kri2.airq.airqapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class WelcomeController
{
    @Autowired
    GiosApiService giosApiService;
    
    @RequestMapping("/")
    public String welcome(Map<String, Object> model){
        model.put("message", "Welcome to airq app!");
        Map<String, String> map = giosApiService.loadDataFromGiosServer();
        model.put("PM2",  map.get("PM2.5"));
        model.put("PM10", map.get("PM10"));
        return "welcome";
    }
}
