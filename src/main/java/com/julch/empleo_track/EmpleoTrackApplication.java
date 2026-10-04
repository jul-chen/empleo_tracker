package com.julch.empleo_track;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
public class EmpleoTrackApplication {

    @Value("${application.id}")
    private String appID;
    @Value("${application.key}")
    private String appKey;


	public static void main(String[] args) {
		SpringApplication.run(EmpleoTrackApplication.class, args);

	}

    //@Scheduled(fixedRate = 60000)

    @GetMapping("/empleos")
    public String extractInfo(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.adzuna.com/v1/api/jobs/es/search/1?app_id={"+ appID + "}&app_key={" + appKey + "}";
        AdzunaResp response = restTemplate.getForObject(url, AdzunaResp.class);
        StringBuilder stringBuilder = new StringBuilder();
        for(AdzunaResp.JobOfferData jobOfferData : response.results()){
            stringBuilder.append(jobOfferData.id());
            stringBuilder.append(",");
            stringBuilder.append(jobOfferData.title());
            stringBuilder.append(",");
            stringBuilder.append(jobOfferData.description());
            stringBuilder.append(",");
            stringBuilder.append(jobOfferData.contract_time());
        }
        return stringBuilder.toString();
    }



}
