package com.julch.empleo_track.service;

import com.julch.empleo_track.client.AdzunaClient;
import com.julch.empleo_track.dto.AdzunaResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobProcessorService {
    private AdzunaClient adzunaClient;

    public JobProcessorService(AdzunaClient adzunaClient) {
        this.adzunaClient = adzunaClient;
    }

    public void iniciarBusquedaOferta(){
        System.out.println("Iniciando busqueda oferta de adzuna");
        AdzunaResponse resultado = adzunaClient.buscarOfertas();
        List<AdzunaResponse.JobOfferData> resultadoArray = resultado.results();

        System.out.println("Resultado " + resultadoArray.size());
        for(AdzunaResponse.JobOfferData jobOfferData : resultadoArray){
            System.out.println(jobOfferData.title() + " - " + jobOfferData.redirect_url());
        }

    }


}
