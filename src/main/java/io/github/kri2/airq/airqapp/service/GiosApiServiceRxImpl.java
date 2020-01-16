package io.github.kri2.airq.airqapp.service;

import io.github.kri2.airq.airqapp.ParamsUrls;
import io.github.kri2.airq.airqapp.response.DateAndValue;
import io.github.kri2.airq.airqapp.response.ParameterReadout;
import io.github.kri2.airq.airqapp.response.ParameterReadoutDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import rx.Single;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GiosApiServiceRxImpl implements GiosApiServiceRx
{
    @Autowired
    RestTemplate restTemplate;
    @Override
    public Single<Set<DateAndValue>> findAll()
    {
        return null;
    }
    
    private Single<Set<ParameterReadout>> findAllAvailable(){
        return Single.create(singleSubscriber -> {// we are implementing "on success" only for now (error left)
            Set<ParameterReadout> set = Stream.of(ParamsUrls.values())
                                          .map(v->findByUrl(v.getUrl()))
                                          .collect(Collectors.toSet());
                singleSubscriber.onSuccess(set);
        });
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
}}
