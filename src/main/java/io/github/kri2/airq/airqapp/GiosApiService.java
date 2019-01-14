package io.github.kri2.airq.airqapp;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class GiosApiService
{
    public Map<String,String> loadDataFromGiosServer(){
        Map<String,String> map = new HashMap<>();
        RestTemplate restTemplate = new RestTemplate();
    
        ParameterItem parameterPm25 = restTemplate.getForObject("http://api.gios.gov.pl/pjp-api/rest/data/getData/2772", ParameterItem.class);
        //System.out.println(parameterPm25.toString());
        // index 0 in List is most current readout, but often its value is nulll, if that's the case read next (up to 5 readouts prior)
        int indexToRead=0;
        while(parameterPm25.getValues().get(indexToRead).getValue()==null && indexToRead<5)
        {
            indexToRead++;
        }
        map.put(parameterPm25.getKey(),parameterPm25.getValues().get(indexToRead).getValue());
        
        
        ParameterItem parameterPm10 = restTemplate.getForObject("http://api.gios.gov.pl/pjp-api/rest/data/getData/2770", ParameterItem.class);
        //System.out.println(parameterPm10.toString());
        // index 0 in List is most current readout, but often its value is nulll, if that's the case read next (up to 5 readouts prior)
        indexToRead=0;
        while(parameterPm10.getValues().get(indexToRead).getValue()==null && indexToRead<5)
        {
            indexToRead++;
        }
        
        map.put(parameterPm10.getKey(),parameterPm10.getValues().get(indexToRead).getValue()); // TODO: for now second, but later to be determind which one is valid and freshest
        
        return map;
    }
}
