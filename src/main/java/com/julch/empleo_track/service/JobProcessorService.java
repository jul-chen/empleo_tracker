package com.julch.empleo_track.service;

import com.julch.empleo_track.client.AdzunaClient;
import com.julch.empleo_track.dto.AdzunaResponse;
import com.julch.empleo_track.dto.JobOfferEntity;
import com.julch.empleo_track.repository.JobOfferRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobProcessorService {

    private AdzunaClient adzunaClient;
    private JobOfferRepository jobOfferRepository;

    public JobProcessorService(AdzunaClient adzunaClient, JobOfferRepository jobOfferRepository) {
        this.adzunaClient = adzunaClient;
        this.jobOfferRepository = jobOfferRepository;
    }

    public void iniciarBusquedaOferta(){

        System.out.println("Iniciando busqueda oferta de adzuna");
        AdzunaResponse resultado = adzunaClient.buscarOfertas();

        for(AdzunaResponse.JobOfferData jobOfferData : resultado.results()){
            if(!jobOfferRepository.existsById(jobOfferData.id())){
                System.out.println("Nueva oferta encontrada: " + jobOfferData.title());
                JobOfferEntity jobOfferEntity = new JobOfferEntity(jobOfferData.id(),jobOfferData.title(),
                        jobOfferData.redirect_url(),jobOfferData.company().company_name());
                jobOfferRepository.save(jobOfferEntity);
            }else{
                System.out.println("Oferta ya guardada: " + jobOfferData.id());
            }
        }

        List<AdzunaResponse.JobOfferData> resultadoArray = resultado.results();

        System.out.println("Resultado " + resultadoArray.size());
        for(AdzunaResponse.JobOfferData jobOfferData : resultadoArray){
            System.out.println(jobOfferData.title() + " - " + jobOfferData.redirect_url());
        }

    }


}
