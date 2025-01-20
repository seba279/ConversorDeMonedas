package com.aluracursos.conversordemonedas.service;


import com.aluracursos.conversordemonedas.model.DatosMoneda;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.gson.Gson;

public class ConvierteDatos implements IConvierteDatos{

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T convertirMoneda(String json, Class<T> clase) {
        try {
            return mapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public DatosMoneda convertirMonedas(String json){
        return new Gson().fromJson(json, DatosMoneda.class);
    }
}
