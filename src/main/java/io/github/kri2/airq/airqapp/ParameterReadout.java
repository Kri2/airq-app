package io.github.kri2.airq.airqapp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ParameterReadout
{
    private String key;
    private String value;
    private String date;
}
