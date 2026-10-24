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

        for(int i = 0 ; i<resultado.results().size() ; i++){
            if(!jobOfferRepository.existsById(resultado.results().get(i).id())){
                JobOfferEntity jobOfferEntity = new JobOfferEntity();
                System.out.println("Nueva oferta encontrada: " + resultado.results().get(i).title());
                jobOfferEntity.setId(resultado.results().get(i).id());
                jobOfferEntity.setTitle(resultado.results().get(i).title());
                jobOfferEntity.setRedirect_url(resultado.results().get(i).redirect_url());
                jobOfferEntity.setCompany(resultado.results().get(i).company().company_name());
                jobOfferRepository.save(jobOfferEntity);

            }else{
                System.out.println("Oferta ya existente");
            }
        }

        List<AdzunaResponse.JobOfferData> resultadoArray = resultado.results();

        System.out.println("Resultado " + resultadoArray.size());
        for(AdzunaResponse.JobOfferData jobOfferData : resultadoArray){
            System.out.println(jobOfferData.title() + " - " + jobOfferData.redirect_url());
        }

    }


}
