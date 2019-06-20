package io.github.kri2.airq.airqapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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
        return urls.entrySet().stream().map(s-> findByUrl(s.getValue()))
                  .collect(Collectors.toMap(par->par.getKey(),par->par.getLastValue().getValue()));
    }
    
    public Set<ParameterReadout> loadToSet(){
        return urls.entrySet().stream().map(s->findByUrl(s.getValue()))
                   .map(this::mapFromDTO)
                   .collect(Collectors.toSet());
    }
    
    private ParameterReadoutDTO findByUrl(String url){
        ParameterReadoutDTO parameterReadoutDTO = restTemplate.getForObject(url, ParameterReadoutDTO.class);
        return parameterReadoutDTO;
    }
    
    public ParameterReadout mapFromDTO(ParameterReadoutDTO parameterReadoutDTO){
        return new ParameterReadout(
            parameterReadoutDTO.getKey(),
            parameterReadoutDTO.getLastValue().getValue(),
            parameterReadoutDTO.getLastValue().getDate()
        );
    }
    
}
