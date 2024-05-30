package com.alura.OmbdAPI.principal;

import java.util.Arrays;
import java.util.List;

public class EjemploStream {
    public void muestraEjemplo(){
        List<String> nombres = Arrays.asList("Brenda", "Luis", "Erik", "Genesys", "Maria Fernanda");

        nombres.stream()
                .sorted()
                .limit(4)
                .filter(n->n.startsWith("G"))
                .map(n->n.toUpperCase())
                .forEach(System.out::println);

    }
}
