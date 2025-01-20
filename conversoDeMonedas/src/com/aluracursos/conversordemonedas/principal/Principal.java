package com.aluracursos.conversordemonedas.principal;

import com.aluracursos.conversordemonedas.model.Datos;
import com.aluracursos.conversordemonedas.model.DatosMoneda;
import com.aluracursos.conversordemonedas.model.Moneda;
import com.aluracursos.conversordemonedas.service.ConsumoAPI;
import com.aluracursos.conversordemonedas.service.ConvierteDatos;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String API_KEY = "TU_API_KEY";
    private final String URL_BASE = "https://v6.exchangerate-api.com/v6/"+ API_KEY +"/pair/";
    private ConvierteDatos conversor = new ConvierteDatos();

    public void mostrarMenu()  {
        try {

            int salir = 0;

            while (salir != 7) {
                System.out.println("***************************************************************");

                System.out.println("                    CONVERSOR DE MONEDA                      \n");
                System.out.println("                1) Dolar ==> Peso Argentino                    ");
                System.out.println("                2) Peso Argentino ==> Dolar                    ");
                System.out.println("                3) Dolar ==> Real Brasilero                    ");
                System.out.println("                4) Real Brasilero ==> Dolar                    ");
                System.out.println("                5) Dolar ==> Peso Colombiano                   ");
                System.out.println("                6) Peso Colombiano ==> Dolar                   ");
                System.out.println("                7) Salir                                     \n");

                System.out.println("****************************************************************\n");

                System.out.println("*************************** DATOS ******************************");
                System.out.println("Elija una opcion: ");
                int opcion = teclado.nextInt();
                String base = "";
                String target = "";
                double amount = 0;

                switch (opcion) {
                    case 1:
                        System.out.println("Ingrese la cantidad en Dolares: ");
                        amount = Double.valueOf(teclado.nextDouble());
                        base = "USD";
                        target = "ARS";
                        break;
                    case 2:
                        System.out.println("Ingrese la cantidad en Pesos Argentinos: ");
                        amount = Double.valueOf(teclado.nextDouble());
                        base = "ARS";
                        target = "USD";
                        break;
                    case 3:
                        System.out.println("Ingrese la cantidad en Dolares: ");
                        amount = Double.valueOf(teclado.nextDouble());
                        base = "USD";
                        target = "BRL";
                        break;
                    case 4:
                        System.out.println("Ingrese la cantidad en Reales: ");
                        amount = Double.valueOf(teclado.nextDouble());
                        base = "BRL";
                        target = "USD";
                        break;
                    case 5:
                        System.out.println("Ingrese la cantidad en Dolares: ");
                        amount = Double.valueOf(teclado.nextDouble());
                        base = "USD";
                        target = "COP";
                        break;
                    case 6:
                        System.out.println("Ingrese la cantidad en Pesos Colombianos: ");
                        amount = Double.valueOf(teclado.nextDouble());
                        base = "COP";
                        target = "USD";
                        break;
                    case 7:
                        salir = 7;
                        break;
                    default:
                        System.out.println("Seleccionaste una opcion invalida");
                        break;
                }

                System.out.println("****************************************************************");

                var json = consumoAPI.obtenerDatos(URL_BASE + base + "/" + target + "/" + amount);
                //System.out.println(json.getClass().getName());
                //System.out.println(json);

                //JsonElement jsonElement = JsonParser.parseString(json);

                //JsonObject jsonObject = jsonElement.getAsJsonObject();
                //System.out.println(jsonObject);

                //Convertimos los datos
                var datos = conversor.convertirMonedas(json);
                //datos.resultados().stream()
                  //      .limit(1)
                    //    .forEach(System.out::println);

                System.out.println(datos);

                //JsonElement jsonElement = JsonParser.parseString(json);

                //JsonObject jsonObject = jsonElement.getAsJsonObject();
                //System.out.println(jsonObject);

                //System.out.println( jsonObject.get("base_code"));
                //System.out.println( jsonObject.get("target_code"));
                //System.out.println( jsonObject.get("conversion_rate"));
                //System.out.println( jsonObject.get("conversion_result"));

                Moneda moneda = new Moneda(base, target, amount);

                //List<Moneda> monedas = new ArrayList<>();

                //monedas.add(moneda);
                //System.out.println(monedas);

                System.out.println(moneda.toString());
                //System.out.println("El tipo de moneda es: " + datos.base_code());
                //System.out.println("La moneda a convertir: " + datos.target_code());
                System.out.println("El resultado de convertir " + amount + " " + "[" + datos.base_code() + "]" + " a " + "[" + datos.target_code() + "]" + " es " + moneda.getResultadoConversion(datos.conversion_rate()) + " " + "[" + datos.target_code() + "]");

                // Crear el archivo JSON
                //ObjectMapper objectMapper = new ObjectMapper();

                // Escribir la lista de conversiones en un archivo JSON
                //try {
                  //  objectMapper.writeValue(new File("conversiones_monedas.json"), monedas);
                    //System.out.println("Lista de conversiones guardada en conversiones_monedas.json");
                //} catch (IOException e) {
                  //  e.printStackTrace();  // Imprime el error completo
                //}

            }
        }catch (RuntimeException  e) {
            System.out.println(e.getMessage());
            System.out.println("Finalizando la aplicacion.");
        }
    }
}
