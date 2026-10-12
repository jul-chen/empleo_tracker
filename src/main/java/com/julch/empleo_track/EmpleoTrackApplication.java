package com.julch.empleo_track;
import com.julch.empleo_track.client.AdzunaClient;
import com.julch.empleo_track.dto.AdzunaResponse;
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
    CommandLineRunner init(AdzunaClient adzunaClient) {
        return args -> {
            System.out.println("Conectando con Adzuna...");
            var respuesta = adzunaClient.buscarOfertas();

            respuesta.results().forEach(oferta -> {
                System.out.println("------------------------------------------------");
                System.out.println(" Puesto: " + oferta.title());
                System.out.println(" Empresa: " + (oferta.company() != null ? oferta.company().company_name() : "Confidencial"));
                System.out.println(" Link: " + oferta.redirect_url());
            });
        };
    }


}
