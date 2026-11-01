package com.julch.empleo_track.service;

import com.julch.empleo_track.client.AdzunaClient;
import com.julch.empleo_track.client.TelegramClient;
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
    private TelegramClient telegramClient;


    public JobProcessorService(AdzunaClient adzunaClient, JobOfferRepository jobOfferRepository, TelegramClient telegramClient) {
        this.adzunaClient = adzunaClient;
        this.jobOfferRepository = jobOfferRepository;
        this.telegramClient = telegramClient;
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
                String textoMensaje = " <b>NUEVA OFERTA DE EMPLEO</b>\n\n" +
                        "<b>Puesto:</b> " + jobOfferData.title() + "\n" +
                        "<b>Empresa:</b> " + jobOfferData.company().company_name() + "\n\n" +
                        "<a href=\"" + jobOfferData.redirect_url() + "\"> Ver Oferta Aquí</a>";
                telegramClient.enviarMensaje(textoMensaje);
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
