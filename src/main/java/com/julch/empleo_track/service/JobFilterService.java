package com.julch.empleo_track.service;

import com.julch.empleo_track.dto.AdzunaResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class JobFilterService {
    public List<AdzunaResponse.JobOfferData> filtrarJobs(List<AdzunaResponse.JobOfferData> ofertasBrutas){
        return ofertasBrutas.stream()
                .filter(this::ofertaValida)
                .toList();
    }


    public boolean ofertaValida(AdzunaResponse.JobOfferData oferta){
        String titulo = oferta.title().toLowerCase();
        boolean esBeca = titulo.contains("beca") ||
                        titulo.contains("práctica") ||
                        titulo.contains("practica") ||
                        titulo.contains("intern");

        boolean esDesarrollo = titulo.contains("desarrollador") ||
                            titulo.contains("java") ||
                            titulo.contains("python") ||
                            titulo.contains("backend") ||
                            titulo.contains("frontend");


        return esBeca && esDesarrollo;
    }


}
