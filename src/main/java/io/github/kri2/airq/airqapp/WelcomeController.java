package io.github.kri2.airq.airqapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
public class WelcomeController
{
    Logger LOGGER = LoggerFactory.getLogger(WelcomeController.class);
    GiosApiService giosApiService;
    
    @Autowired
    public WelcomeController(GiosApiService giosApiService){
        this.giosApiService = giosApiService;
    }
    
    Map<String,String> urls = new HashMap<String,String>(){{
        put("pm25","http://api.gios.gov.pl/pjp-api/rest/data/getData/2772");
        put("pm10", "http://api.gios.gov.pl/pjp-api/rest/data/getData/2770");
    }};
    
    @RequestMapping("/")
    public String welcome(Map<String, Object> model){
        model.put("message", "Welcome to airq app!");
        Set<ParameterReadout> set = giosApiService.findAll(urls);
        set.stream().forEach(x->System.out.println(x.getKey()+x.getValue()));
        model.put("VALUES",set);
        return "welcome";
    }
    
    @ExceptionHandler(HttpClientErrorException.class)
    public String handleClientError(Model model) {
        model.addAttribute("error_message",
                           "page not found, or other error.");
        return "page_not_found";
    }
    
    @ExceptionHandler(UnknownHostException.class)
    public String hadleGiosApiError(Model model){
        model.addAttribute("error_message",
                           "GIOS server not reachable");
        return "api_not_available";
    }
}
