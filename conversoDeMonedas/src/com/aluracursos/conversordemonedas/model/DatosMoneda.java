package com.aluracursos.conversordemonedas.model;

public record DatosMoneda(
        String base_code,
        String target_code,
        double conversion_rate,
        double conversion_result) {
}
