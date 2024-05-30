package com.alura.OmbdAPI.principal;

import ch.qos.logback.classic.spi.EventArgUtil;
import com.alura.OmbdAPI.model.DatosEpisodio;
import com.alura.OmbdAPI.model.DatosSerie;
import com.alura.OmbdAPI.model.DatosTemporadas;
import com.alura.OmbdAPI.model.Episodio;
import com.alura.OmbdAPI.service.ConsumoAPI;
import com.alura.OmbdAPI.service.ConvierteDatos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner lectura = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=18f2a349";
    private ConvierteDatos conversor = new ConvierteDatos();

    public void mustraElMenu(){
        System.out.println("Escribe el nombre de la serie:");
        //Busca los datos generales de nuestra serie
        var nombreSerie = lectura.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + API_KEY);
        var datos = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println(datos);

        //Busca los datos de todas las temporadas
        List<DatosTemporadas> temporadas = new ArrayList<>();
        for (int i = 1; i <= datos.totalDeTemporadas(); i++) {
            json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + "&Season=" + i + API_KEY);
            var datosTemporadas = conversor.obtenerDatos(json, DatosTemporadas.class);
            temporadas.add(datosTemporadas);
        }
        //temporadas.forEach(System.out::println);
        
        //Mostrar solo el titulo del episodio para las temporadas
//        for (int i = 0; i < datos.totalDeTemporadas(); i++) {
//            List<DatosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
//            for (int j = 0; j < episodiosTemporada.size(); j++) {
//                System.out.println(episodiosTemporada.get(j).titulo());
//            }
//        }
        //Función lamba que reemplaza nuetra lista dentro del for anterior
        //temporadas.forEach(t -> t.episodios().forEach(e-> System.out.println(e.titulo())));

        //Convertir todas las informaciones a una lista del tipo DatosEpisodio

        List<DatosEpisodio> datosEpisodios=temporadas.stream()
                .flatMap(t-> t.episodios().stream())
                .collect(Collectors.toList());

        //Top 5 episodios
//        System.out.println("Top 5");
//        datosEpisodios.stream()
//                .filter(e->!e.evaluacion().equalsIgnoreCase("N/A"))
//                .peek(e-> System.out.println("Primer filtro (N/A) " + e))
//                .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed())
//                .peek(e-> System.out.println("Segundo filtro ordenación (M>m) " + e))
//                .map(e->e.titulo().toUpperCase())
//                .peek(e-> System.out.println("Tercer filtro Mayúscula (m>M)" + e))
//                .limit(5)
//                .forEach(System.out::println);

        //Convirtiendo los datos a una lista del tipo Episodio
        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d->  new Episodio(t.numeroTemporada(),d)))
                .collect(Collectors.toList());

//        episodios.forEach(System.out::println);

        //Busqueda de episodios a partir de x año
//        System.out.println("Indica el año del episodio");
//        var fecha = lectura.nextInt();
//        lectura.nextLine();

//        LocalDate fechaBusqueda = LocalDate.of(fecha, 1, 1);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        episodios.stream()
//                .filter(e->e.getFechaDeLanzamiento() != null && e.getFechaDeLanzamiento().isAfter(fechaBusqueda))
//                .forEach(e-> System.out.println(
//                        "Temporada: "+e.getTemporada()+
//                                " Episodio: "+e.getTitulo()+
//                                " Fecha de Lanzamiento: " + e.getFechaDeLanzamiento().format(dtf)
//                ));

        //Buqueda por palabra clave
//        System.out.println("Ingresa la palabra clave: ");
//        var palabraclava = lectura.nextLine();
//        Optional<Episodio> episodioBuscado = episodios.stream()
//                .filter(e -> e
//                        .getTitulo().toUpperCase()
//                        .contains(palabraclava.toUpperCase()))
//                .findFirst();
//        if (episodioBuscado.isPresent()){
//            System.out.println("Espisodio encontrado");
//            System.out.println("Los datos son: " + episodioBuscado.get());
//        }else {
//            System.out.println("Espidio no encontrado");
//        }
        Map<Integer, Double> evaluacionesPorTemporada = episodios.stream()
                .filter(e -> e.getEvalucion() > 0.0)
                .collect(Collectors.groupingBy(Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getEvalucion)));
        System.out.println(evaluacionesPorTemporada);
        DoubleSummaryStatistics est = episodios.stream()
                .filter(e -> e.getEvalucion() > 0.0)
                .collect(Collectors.summarizingDouble(Episodio::getEvalucion));
        System.out.println("Media: " + est.getAverage());
        System.out.println("Episodio mejor evaluado: " + est.getMax());
        System.out.println("Episodio peor evaluado: " + est.getMin());
    }
}
