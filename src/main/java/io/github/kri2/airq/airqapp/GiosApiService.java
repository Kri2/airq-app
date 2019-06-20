package io.github.kri2.airq.airqapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GiosApiService
{
    @Autowired
    RestTemplate restTemplate;
    
    Map<String,String> urls = new HashMap<String,String>(){{
        put("pm2.5","http://api.gios.gov.pl/pjp-api/rest/data/getData/2772");
        put("pm10", "http://api.gios.gov.pl/pjp-api/rest/data/getData/2770");
    }};

    public Map<String,String> loadDataFromGiosServer(){
        return urls.entrySet().stream().map(s->getOneParameterItem(s.getValue()))
                  .collect(Collectors.toMap(par->par.getKey(),par->par.getLastValue().getValue()));
    }
    
    private ParameterItem getOneParameterItem(String url){
        ParameterItem parameterItem = restTemplate.getForObject(url,ParameterItem.class);
        return parameterItem;
    }
}
