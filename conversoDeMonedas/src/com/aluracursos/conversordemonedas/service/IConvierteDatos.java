package com.aluracursos.conversordemonedas.service;

public interface IConvierteDatos {
    <T> T convertirMoneda(String json, Class<T> clase);
}
