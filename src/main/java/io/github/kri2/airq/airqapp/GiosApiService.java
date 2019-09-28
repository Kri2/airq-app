package io.github.kri2.airq.airqapp;

import java.util.Set;

public interface GiosApiService{
    public ParameterReadout findByUrl(String url);
    public Set<ParameterReadout> findAll();
}