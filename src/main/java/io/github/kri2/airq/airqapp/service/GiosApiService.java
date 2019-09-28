package io.github.kri2.airq.airqapp.service;

import io.github.kri2.airq.airqapp.response.ParameterReadout;

import java.util.Set;

public interface GiosApiService{
    Set<ParameterReadout> findAll();
}