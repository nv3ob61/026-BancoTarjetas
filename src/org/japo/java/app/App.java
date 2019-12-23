/*
 * Copyright 2019 mon_mo.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.japo.java.app;

import java.util.Locale;
import org.japo.java.libraries.UtilesEntrada;

/**
 *
 * @author mon_mo
 */
public final class App {

    public static final String PACO = "---";
    public static final String PACO_LONG = "-----------------------------";
    public static final String MSG_USR = "Introduzca una opción: ";
    public static final String MSG_TARJETA = "Introduza el nº de la tarjeta: ";
    public static final String MSG_SALDO = "Introduza el saldo de "
            + "la cuenta nº ";
    public static final String MSG_ERR = "ERROR: dato introducido no válido";
    public static final String MSG_SENDER = "Introduce el num del que envia: ";
    public static final String MSG_RECEIVER = "Introduce el num del destino: ";
    public static final String MSG_CANTIDAD = "El usuario X envia la cantidad: ";
    public static final String MSG_MUESTRA = "Introduce el número de tarjeta "
            + "(0 para salir): ";

    public static boolean test;
    public static int i;
    public static int j;

    public final void launchApp() {
        int numeroTarjeta[];
        int num;
        double saldoTarjeta[];
        //double medMaxMin[];

        //Array de 10 objetos, enough...
        numeroTarjeta = new int[10];
        saldoTarjeta = new double[10];
        //inicializamos tres tarjetas
        numeroTarjeta[0] = 1111;
        numeroTarjeta[1] = 2222;
        numeroTarjeta[2] = 3333;
        //saldo de las tarjetas
        saldoTarjeta[0] = 1000;
        saldoTarjeta[1] = 2000;
        saldoTarjeta[2] = 3000;

        //***********************************************************************
        //                          INICIO DEL PROGRAMA
        //***********************************************************************
        muestraBanner();

        do {
            //Faltan varios métodos por finalizar... :)
            muestraOpciones();
            num = UtilesEntrada.leerEntero(MSG_USR, MSG_ERR);
            switch (num) {
                case 1:
                    nuevaTarjeta(numeroTarjeta, saldoTarjeta, test);
                    ordenaTarjetas(numeroTarjeta, saldoTarjeta);
                    break;
                case 3:
                    muestraCuenta(numeroTarjeta, saldoTarjeta);
                    break;
                case 4:
                    muestraTotales(numeroTarjeta, saldoTarjeta);
                    break;
                case 9:
                    transaction(numeroTarjeta, saldoTarjeta);
                    break;
                case 0:
                    System.out.println("FIN DEL PROGRAMA.");
                    break;
                default:
                    System.out.printf("%nERROR: Opción incorrecta%n");
            }
        } while (num != 0);
    }

    public final void muestraBanner() {
        System.out.println("-------------------------------------"
                + "----------------");
        System.out.println("\t TU CRIPTOBANCO DE CONFIANZA");
        System.out.println("-------------------------------------"
                + "----------------");
    }

    public final void muestraCuenta(int num[], double sal[]) {
        int numero;
        boolean localizada;
        boolean existe;
        localizada = false;
        existe = false;

        //Comprobamos si la primera posición del array es un 0, vacío...
        if (num[0] == 0) {
            System.out.println("ERROR: Memoria vacía.");
        } else {
            do {
                System.out.println(PACO);
//                System.out.println("MOSTRAR tarjeta BANNER!! AQUI, EDIT.");
                numero = UtilesEntrada.leerEntero(MSG_MUESTRA, MSG_ERR);
                if (numero > 999999 || numero < 0) {
                    System.out.println("ERROR: valor fuera de rango");
                } else {
                    if (numero == 0) {
                        //Vacío. Salta a casilla de salida.
                    } else {
                        //Sino busca y muestra el resultado de la búsqueda.
                        for (int g = 0; g < num.length || localizada; g++) {
                            if (numero == num[g]) {
                                existe = true;
                                System.out.println("El saldo de la tarjeta Nº "
                                        + num[g] + " es de " + sal[g] + "€.");
                            }
                        }
                        //Si no existe
                        if (!existe) {
                            System.out.println("El número de tarjeta introducido "
                                    + "no se encuentra en la lista.");
                        }
                    }
                }
            } while (numero > 999999 && numero < 1 || !existe && numero != 0);
        }
    }

    public final void muestraOpciones() {
        System.out.println(PACO);
        System.out.println("1. Nueva tarjeta.");
        System.out.println("2. Eliminar tarjeta.");
        System.out.println("3. Mostrar una cuenta.");
        System.out.println("4. Ver información totales.");
        System.out.println("5. Calcular saldo medio, máximo y mínimo.");
        System.out.println("6. Mostrar todas las cuentas.");
        System.out.println("9. Transacciones.");
        System.out.println("0. FIN.");
        System.out.println(PACO);
    }

    public final void muestraTotales(int num[], double sal[]) {
        int totalCuentas = 0;
        double totalSaldos = 0;

        for (int f = 0; f < num.length; f++) {
            if (num[f] != 0) {
                totalCuentas++;
            }
            totalSaldos += sal[f];
        }
        System.out.println(PACO_LONG);
        System.out.println("  INFO € TOTALES EN CUENTAS");
        System.out.println(PACO_LONG);
        System.out.printf("Hay un total de %d cuentas activas%n", totalCuentas);
        System.out.println(PACO);
        System.out.printf(Locale.ENGLISH, "El saldo total de las cuentas es"
                + " de %.2f €%n", totalSaldos);
    }

    public static final void nuevaTarjeta(int num[],
            double sal[], boolean test) {
        //Primera comprobación:
        //   ******* Si la memoria está llena salta el mensaje  *******
        if (num[(num.length - 1)] != 0) {
            System.out.println("No se puede introducir una nueva tarjeta.");
            System.out.println("!!!Memoria llena!!");
            //En caso contrario pasa a validar la tarjeta.
        } else {
            do {
                int temp
                        = UtilesEntrada.leerEntero(MSG_TARJETA, MSG_ERR);
                //Salta al método check para validar que la tarjeta introducida
                //está, o no, en el array. EN UNA VARIABLE TEMPORAL.
                if (check(num, temp) == true) {
                    System.out.println("NÚMERO REPETIDO");
                } else {
                    //Una vez validado el valor de la variable asignado a temp
                    //pasa a la última posición del array definitivamente.
                    (num[(num.length - 1)]) = temp;
                    if (num[(num.length - 1)] > 999999
                            || num[(num.length - 1)] < 1) {
                        System.out.println("El número no puede ser "
                                + "ni menor que 1 ni mayor que 999999.");
                    }
                }
            } while (num[(num.length - 1)] > 999999
                    || num[(num.length - 1)] < 1);
            do {
                sal[(sal.length - 1)]
                        = UtilesEntrada.leerEntero(String.format("%s%d: ",
                                MSG_SALDO, (num[(num.length - 1)])), MSG_ERR);
                if (sal[(sal.length - 1)] <= 0) {
                    System.out.println("El saldo inicial no puede ser "
                            + "ni 0 ni negativo.");
                }
            } while (sal[(sal.length - 1)] <= 0);
            System.out.println(PACO);
            System.out.println("Tarjeta añadida con éxito.");
            System.out.println(PACO);
        }
    }

    public final void ordenaTarjetas(int num[], double sal[]) {
        // Organizamos almacenando el valor que no tiene que estar es ese sitio, 
        // en una variable para despues colocarlo en su lugar 
        // y así recorremos el array tantas veces como celdas tiene para 
        // realizar esta comprobación.
        // Realizamos el mismo movimiento en el array perteneciente a los 
        // saldos para que coincidan con los de sus cuentas respectivas  
        int almaNum;
        double almaSal;
        for (int f = 0; f < (num.length - 1); f++) {
            for (int g = f + 1; g < num.length; g++) {
                if (num[f] < num[g]) {
                    almaNum = num[f];
                    almaSal = sal[f];
                    num[f] = num[g];
                    sal[f] = sal[g];
                    num[g] = almaNum;
                    sal[g] = almaSal;
                }
            }
        }
    }

    //***********************************************************************
    //                              MISC.
    //***********************************************************************
    // comprueba si el elemento específico está presente en el array o no 
    // usando un método de Búsqueda Lineal
    private static boolean check(int[] arr, int toCheckValue) {
        test = false;
        for (int element : arr) {
            if (element == toCheckValue) {
                test = true;
                break;
            }
        }
        // A MODO TEST: imprime el resultado . A MODO TEST. 
        System.out.println("Is " + toCheckValue
                + " present in the array: " + test);
        return test;
    }

    //Método para hacer transferencias entre cuentas.
    public static final void transaction(int num[], double sal[]) {
        //los actores
        int sender;
        int receiver;
        double send = 0;
        //El remitente
        sender = UtilesEntrada.leerEntero(MSG_SENDER, MSG_ERR);
        //comprobamos que la dirección existe
        if (check(num, sender) == true) {
            //buscamos la posición de la cuenta del remitente
            for (i = 0; i < num.length; i++) {
                //Cuando coincide la dirección de envio en el array 
                // empieza la fiesta.
                if (sender == num[i]) {
                    System.out.println("Número del remitente: " + sender);
                    do {
                        //Determina el valor de la transferencia.
                        send = UtilesEntrada.leerReal(MSG_CANTIDAD, MSG_ERR);
                        if (send > sal[i]) {
                            System.out.println("Saldo insuficiente");
                        }
                    } while (send < 0 || send > sal[i]);
                    //Comprobamos aquí que el destinatario existe también.
                    receiver = UtilesEntrada.leerEntero(MSG_RECEIVER, MSG_ERR);
//***   Si el destinatario es correcto NO REPITE, SALE AL MENÚ. Si? ARREGLAR....
                    if (check(num, receiver) == true) {
                        //Busca la posición de cuenta destino
                        for (j = 0; j < num.length; j++) {
                            //Cuando coincide hace la suma de lo que se envia
                            //Y se resta de la cuenta del remitente.
                            if (receiver == num[j]) {
                                System.out.println("Número del destino: "
                                        + receiver);
                                sal[j] += send;
                                sal[i] = sal[i] - send;
                            }
                        }
                        //no documentamos estas líneas siguientes porque se
                        //puede intuir de dónde procede cada mensaje.
                    } else {
                        System.out.println("ERROR: Número destino incorrecto.");
                    }
                    System.out.println("MENSAJE: Transacción OK");
                }
            }
        } else {
            System.out.println("ERROR: Número remitente incorrecto.");
        }
    }
}
