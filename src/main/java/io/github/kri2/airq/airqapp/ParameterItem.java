package io.github.kri2.airq.airqapp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ParameterItem
{
    private String key;
    private List<DateAndValue> values;
    
    public String getKey()
    {
        return key;
    }
    
    public void setKey(String key)
    {
        this.key = key;
    }
    
    public List<DateAndValue> getValues()
    {
        return values;
    }
    
    public void setValues(List<DateAndValue> values)
    {
        this.values = values;
    }
    
    public DateAndValue getLastValue(){
        // index 0 in List is most current readout, but often its value is null, if that's the case read next (up to 5 readouts prior)
        for(int indexToReadFrom=0;indexToReadFrom<5;indexToReadFrom++){
            if(this.getValues().get(indexToReadFrom).getValue() != null)
                return this.getValues().get(indexToReadFrom);
        }
        return this.getValues().get(0);
    }
    
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append(this.key);
        result.append(this.values.get(1));
        return result.toString();
    }
}
