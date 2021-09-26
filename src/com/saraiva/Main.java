package com.saraiva;

import java.util.Arrays;

public class Main {

    private static final int BIAS = -1;
    private static final double TAXA_TREINAMENTO = 0.05;
    private String teste;

    public static void main(String[] args) {
        // w
        double[] pesosIniciais = new double[]{0.34, -0.23, 0.94, 0.05};
        principal(pesosIniciais);
    }

    // pesoAtual = pesoAnterior + taxaTreinamento * (d - y) * x
    public static double[] treinar(double[] pesosAnteriores, int saidaDesejada, double saidaObtida, double[] x) {
        var valorParaMultiplicarX = TAXA_TREINAMENTO * (saidaDesejada - saidaObtida);
        var xMultiplicado = new double[x.length];
        for (int i = 0; i < x.length; i++){
            xMultiplicado[i] = x[i] * valorParaMultiplicarX;
        }
        var pesosAtuais = new double[xMultiplicado.length];
        for (int i = 0; i < xMultiplicado.length; i++){
            pesosAtuais[i] = xMultiplicado[i] + pesosAnteriores[i];
        }
        return pesosAtuais;
    }

    public static double calcular(double[] entradas, double[] pesos){
        var result = new double[entradas.length];
        for (int i = 0; i < result.length; i++){
            result[i] = entradas[i] * pesos[i];
        }
        return Arrays.stream(result).sum();
    }

    public static void principal(double[] pesos) {
        // d
        var saidaDesejada = -1;
        // x ou u
        double[] entradas = new double[]{BIAS, 0.1, 0.4, 0.7};

        var res = calcular(entradas, pesos);
        if (res < 0){
            System.out.println("=====SUCESSO=====");
            System.out.println("Saída desejada: " + saidaDesejada);
            System.out.println("Entradas: " + Arrays.toString(entradas));
            System.out.println("Pesos ideais: " + Arrays.toString(pesos));
            System.out.println("Valor de u: " + res);
        } else {
            System.out.println("=====ERRADO=====");
            System.out.println("Saída desejada: " + saidaDesejada);
            System.out.println("Entradas: " + Arrays.toString(entradas));
            System.out.println("Pesos: " + Arrays.toString(pesos));
            System.out.println("Valor de u: " + res);
            var result = treinar(pesos, saidaDesejada, 1, entradas);
            principal(result);
        }
    }
}
