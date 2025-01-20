package com.aluracursos.conversordemonedas.model;

import com.aluracursos.conversordemonedas.service.IMoneda;

public class Moneda implements IMoneda {
    private String tipoMoneda;
    private String tipoMonedaConvertir;
    private double monto;

    public Moneda(String tipoMoneda, String tipoMonedaConvertir, double monto) {
        this.tipoMoneda = tipoMoneda;
        this.tipoMonedaConvertir = tipoMonedaConvertir;
        this.monto = monto;
    }

    public String getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getTipoMonedaConvertir() {
        return tipoMonedaConvertir;
    }

    public void setTipoMonedaConvertir(String tipoMonedaConvertir) {
        this.tipoMonedaConvertir = tipoMonedaConvertir;
    }

    public double convertir(double valor){
        return monto * valor;
    }

    @Override
    public  String toString() {
        return  "El tipo de moneda: " + "["+tipoMoneda+"]\n" +
                "La moneda a convertir: " + "["+tipoMonedaConvertir+"]\n" +
                "El monto ingresado es: " + monto +" [" + getTipoMoneda() + "]" ;
    }

    @Override
    public double getResultadoConversion(double valor) {
        return monto * valor;
    }
}
