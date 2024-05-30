package com.alura.OmbdAPI.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
