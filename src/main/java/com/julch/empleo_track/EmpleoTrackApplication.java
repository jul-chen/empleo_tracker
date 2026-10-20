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
public class EmpleoTrackApplication {


	public static void main(String[] args) {
		SpringApplication.run(EmpleoTrackApplication.class, args);
	}

    @Bean
    CommandLineRunner init(JobProcessorService jobProcessorService) {
        return args ->  {
            System.out.println("Iniciando empleo track");
            jobProcessorService.iniciarBusquedaOferta();
        };
    }
}
