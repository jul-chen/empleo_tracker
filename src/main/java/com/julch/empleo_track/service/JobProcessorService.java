package com.julch.empleo_track.service;

import com.julch.empleo_track.client.AdzunaClient;
import com.julch.empleo_track.dto.AdzunaResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobProcessorService {
    private AdzunaClient adzunaClient;
    private JobFilterService jobFilterService;

    public JobProcessorService(AdzunaClient adzunaClient, JobFilterService jobFilterService) {
        this.adzunaClient = adzunaClient;
        this.jobFilterService = jobFilterService;
    }

    public void iniciarBusquedaOferta(){
        System.out.println("Iniciando busqueda oferta de adzuna");
        AdzunaResponse resultado = adzunaClient.buscarOfertas();

        System.out.println("Resultado: " + resultado);
        System.out.println("Iniciando filtro de oferta de adzuna");
        List<AdzunaResponse.JobOfferData> listaFiltrada = jobFilterService.filtrarJobs(resultado.results());
        System.out.println("Resultado " + listaFiltrada.size());

        listaFiltrada.forEach(jobOfferData -> {
            System.out.println(jobOfferData.title());
            System.out.println(jobOfferData.redirect_url());
        });
    }


}
