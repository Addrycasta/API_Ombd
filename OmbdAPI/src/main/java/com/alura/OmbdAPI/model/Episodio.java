package com.alura.OmbdAPI.model;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Episodio {
    private Integer temporada;
    private String titulo;
    private Integer numeroDeEpisodio;
    private Double evalucion;
    private LocalDate fechaDeLanzamiento;

    public Episodio(Integer integer, DatosEpisodio d) {
        this.temporada = integer;
        this.titulo = d.titulo();
        this.numeroDeEpisodio = d.numeroDeEpsodio();
        try {
            this.evalucion = Double.valueOf(d.evaluacion());
        }catch (NumberFormatException e){
            this.evalucion = 0.0;
        }
        try {
            this.fechaDeLanzamiento = LocalDate.parse(d.fechaDeLanzamiento());
        }catch (DateTimeException e){
            this.fechaDeLanzamiento = null;
        }

    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumeroDeEpisodio() {
        return numeroDeEpisodio;
    }

    public void setNumeroDeEpisodio(Integer numeroDeEpisodio) {
        this.numeroDeEpisodio = numeroDeEpisodio;
    }

    public Double getEvalucion() {
        return evalucion;
    }

    public void setEvalucion(Double evalucion) {
        this.evalucion = evalucion;
    }

    public LocalDate getFechaDeLanzamiento() {
        return fechaDeLanzamiento;
    }

    public void setFechaDeLanzamiento(LocalDate fechaDeLanzamiento) {
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    @Override
    public String toString() {
        return
                "temporada=" + temporada +
                ", titulo='" + titulo + '\'' +
                ", numeroDeEpisodio=" + numeroDeEpisodio +
                ", evalucion=" + evalucion +
                ", fechaDeLanzamiento=" + fechaDeLanzamiento;
    }
}
