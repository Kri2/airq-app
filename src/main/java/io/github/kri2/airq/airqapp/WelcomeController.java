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
    private Logger LOGGER = LoggerFactory.getLogger(WelcomeController.class);
    private GiosApiService giosApiService;
    
    @Autowired
    public WelcomeController(GiosApiService giosApiService){
        this.giosApiService = giosApiService;
    }
    
    /*
    @Autowired  chyba tak powinno być. Jak to wstrzykiwać
    ParamsMap paramsMap;
    */
    @RequestMapping("/")
    public String welcome(Map<String, Object> model){
        model.put("message", "Welcome to airq app!");
        Set<ParameterReadout> parametersSet = giosApiService.findAll();
        model.put("VALUES", parametersSet);
        parametersSet.forEach(x->LOGGER.info(x.getKey() + x.getValue()));
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
