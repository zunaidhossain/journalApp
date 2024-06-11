package com.edigest.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProgrammingQuotes {
    @JsonProperty("author")
    private String name;
    @JsonProperty("quote")
    private String content;
}
