package com.julch.empleo_track;
import com.julch.empleo_track.client.AdzunaClient;
import com.julch.empleo_track.dto.AdzunaResponse;
import com.julch.empleo_track.service.JobProcessorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class EmpleoTrackApplication implements CommandLineRunner {

    private final AdzunaClient adzunaClient;
    private final JobProcessorService jobProcessorService;

    public EmpleoTrackApplication(AdzunaClient adzunaClient, JobProcessorService jobProcessorService) {
        this.adzunaClient = adzunaClient;
        this.jobProcessorService = jobProcessorService;
    }

	public static void main(String[] args) {
		SpringApplication.run(EmpleoTrackApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Iniciando empleo track");
        adzunaClient.buscarOfertas();
        jobProcessorService.iniciarBusquedaOferta();
    }
}
