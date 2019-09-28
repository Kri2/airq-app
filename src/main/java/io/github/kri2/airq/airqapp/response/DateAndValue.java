package io.github.kri2.airq.airqapp.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DateAndValue
{
    private String date;
    private String value;
    
    @Override
    public String toString(){
        return this.date+" query result: "+this.value;
    }
}
