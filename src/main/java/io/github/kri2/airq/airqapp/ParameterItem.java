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
    
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append(this.key);
        result.append(this.values.get(1));
        return result.toString();
    }
}
