package io.github.kri2.airq.airqapp.service;

import io.github.kri2.airq.airqapp.ParamsUrls;
import io.github.kri2.airq.airqapp.response.ParameterReadout;
import io.github.kri2.airq.airqapp.response.ParameterReadoutDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GiosApiServiceImpl implements GiosApiService
{
    @Autowired
    RestTemplate restTemplate;
    
    
    
    public Set<ParameterReadout> findAll(){
        return Stream.of(ParamsUrls.values())
                     .map(v->findByUrl(v.getUrl()))
                     .collect(Collectors.toSet());
    }
    
    private ParameterReadout findByUrl(String url){
        return mapFromDTO(restTemplate.getForObject(url, ParameterReadoutDTO.class));
    }
    
    private ParameterReadout mapFromDTO(ParameterReadoutDTO parameterReadoutDTO){
        return new ParameterReadout(
            parameterReadoutDTO.getKey(),
            parameterReadoutDTO.getLastValue().getValue(),
            parameterReadoutDTO.getLastValue().getDate()
        );
    }
    
}
