package com.alura.OmbdAPI;

import com.alura.OmbdAPI.model.DatosEpisodio;
import com.alura.OmbdAPI.model.DatosSerie;
import com.alura.OmbdAPI.model.DatosTemporadas;
import com.alura.OmbdAPI.principal.EjemploStream;
import com.alura.OmbdAPI.service.ConsumoAPI;
import com.alura.OmbdAPI.service.ConvierteDatos;
import com.alura.OmbdAPI.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class OmbdApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(OmbdApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.mustraElMenu();
//		EjemploStream ejemploStream = new EjemploStream();
//		ejemploStream.muestraEjemplo();
	}
}
