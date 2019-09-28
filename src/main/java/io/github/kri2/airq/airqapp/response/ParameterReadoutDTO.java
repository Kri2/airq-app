package io.github.kri2.airq.airqapp.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.kri2.airq.airqapp.response.DateAndValue;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParameterReadoutDTO
{
    private String key;
    private List<DateAndValue> values;
    
    public DateAndValue getLastValue(){
        // index 0 in List is most current readout, but often its value is null, if that's the case read next
        return values.stream()
                     .filter(v->v.getValue()!=null).findFirst().orElse(null); // TODO: consider orElseThrow here
    }
    
    @Override
    public String toString(){
        return this.key + this.values.get(1);
    }
}
