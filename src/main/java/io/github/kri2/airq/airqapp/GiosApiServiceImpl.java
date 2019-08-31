package io.github.kri2.airq.airqapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GiosApiServiceImpl implements GiosApiService
{
    @Autowired
    RestTemplate restTemplate;
    
    
    
    public Set<ParameterReadout> findAll(Map<String, String> urlsMap){
        return urlsMap.entrySet().stream()
                      .map(s->findByUrl(s.getValue()))
                      .collect(Collectors.toSet());
    }
    
    public ParameterReadout findByUrl(String url){
        return mapFromDTO(restTemplate.getForObject(url,ParameterReadoutDTO.class));
    }
    
    public ParameterReadout mapFromDTO(ParameterReadoutDTO parameterReadoutDTO){
        return new ParameterReadout(
            parameterReadoutDTO.getKey(),
            parameterReadoutDTO.getLastValue().getValue(),
            parameterReadoutDTO.getLastValue().getDate()
        );
    }
    
}
