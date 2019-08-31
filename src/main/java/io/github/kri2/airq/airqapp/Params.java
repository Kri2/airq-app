package io.github.kri2.airq.airqapp;

import lombok.Getter;

@Getter
public enum Params
{
    PM2_5("http://api.gios.gov.pl/pjp-api/rest/data/getData/2772"),
    PM10("http://api.gios.gov.pl/pjp-api/rest/data/getData/2770");
    
    Params(String param){
        this.url = param;
    }
    private String url;
}
