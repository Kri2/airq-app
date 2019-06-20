package io.github.kri2.airq.airqapp;

import java.util.Set;

public interface GiosApiService{
    public ParameterReadoutDTO findByUrl(String url);
    public Set<ParameterReadout> loadToSet();
    public ParameterReadout mapFromDTO(ParameterReadoutDTO parameterReadoutDTO);
}