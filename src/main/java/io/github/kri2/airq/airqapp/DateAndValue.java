package io.github.kri2.airq.airqapp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DateAndValue
{
    String date;
    String value;
    
    @Override
    public String toString(){
        return this.date+" query result: "+this.value;
    }
}
