package com.julch.empleo_track.client;

import com.julch.empleo_track.dto.AdzunaResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AdzunaClient {
    private final RestClient restClient;

    @Value("${application.id}")
    private String applicationId;

    @Value("${application.key}")
    private String applicationKey;

    public AdzunaClient() {
        this.restClient = RestClient.builder().baseUrl("https://api.adzuna.com/v1/api/jobs/es").build();
    }

    public AdzunaResponse buscarOfertas(){
        return restClient.get().uri(uriBuilder -> uriBuilder.path("/search/1")
                .queryParam("app_id",applicationId)
                .queryParam("app_key",applicationKey)
                .queryParam("what","java junior")
                        .build())
                .retrieve()
                .body(AdzunaResponse.class);
    }


}
