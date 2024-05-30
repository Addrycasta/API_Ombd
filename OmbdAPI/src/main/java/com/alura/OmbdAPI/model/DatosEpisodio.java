package com.alura.OmbdAPI.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosEpisodio(
        @JsonAlias("Title") String titulo,
        @JsonAlias("Episode") Integer numeroDeEpsodio,
        @JsonAlias("imdbRating") String evaluacion,
        @JsonAlias("Released") String fechaDeLanzamiento
) {
}
