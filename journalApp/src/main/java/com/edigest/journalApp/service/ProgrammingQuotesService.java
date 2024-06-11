package com.edigest.journalApp.service;

import com.edigest.journalApp.api.response.ProgrammingQuotes;
import com.edigest.journalApp.cache.AppCache;
import com.edigest.journalApp.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class ProgrammingQuotesService {

    @Value("${quotes.api}")
    private String API;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    public ProgrammingQuotes getQuote(String name) {
        String finalApi = appCache.APP_CACHE.get(AppCache.key.QUOTES_API.toString()).replace(Placeholders.NAME, name);
        ResponseEntity<ProgrammingQuotes> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, ProgrammingQuotes.class);
        return response.getBody();
    }

}
