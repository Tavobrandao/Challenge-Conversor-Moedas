package br.com.conversor;

import com.google.gson.Gson;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        ConsultaApi consulta = new ConsultaApi();
        Gson gson = new Gson();

        String jsonResposta = consulta.obterDados("USD");
        MoedaApiResponse resposta = gson.fromJson(jsonResposta, MoedaApiResponse.class);
        Map<String, Double> taxas = resposta.taxasDeConversao();

        while (true) {
            exibirMenu();
            try {
                int opcao = leitura.nextInt();

                if (opcao == 7) {
                    System.out.println("\nObrigado por usar o Conversor de Moedas. Até logo!");
                    break;
                }

                String moedaOrigem = "";
                String moedaDestino = "";

                switch (opcao) {
                    case 1:
                        moedaOrigem = "USD";
                        moedaDestino = "BRL";
                        break;
                    case 2:
                        moedaOrigem = "BRL";
                        moedaDestino = "USD";
                        break;
                    case 3:
                        moedaOrigem = "USD";
                        moedaDestino = "ARS";
                        break;
                    case 4:
                        moedaOrigem = "ARS";
                        moedaDestino = "USD";
                        break;
                    case 5:
                        moedaOrigem = "USD";
                        moedaDestino = "EUR";
                        break;
                    case 6:
                        moedaOrigem = "EUR";
                        moedaDestino = "BRL";
                        break;
                    default:
                        System.out.println("Opção inválida! Por favor, escolha um número de 1 a 7.");
                        continue;
                }

                realizarConversao(moedaOrigem, moedaDestino, taxas, leitura);

            } catch (InputMismatchException e) {
                System.out.println("Erro: Por favor, digite um número válido para a opção.");
                leitura.next();
            }
        }
        leitura.close();
    }

    private static void exibirMenu() {
        System.out.println("\nBem-vindo(a) ao Conversor de Moedas!");
        System.out.println("Escolha uma das opções abaixo:");
        System.out.println("\n1) Dólar Americano (USD) => Real Brasileiro (BRL)");
        System.out.println("2) Real Brasileiro (BRL) => Dólar Americano (USD)");
        System.out.println("3) Dólar Americano (USD) => Peso Argentino (ARS)");
        System.out.println("4) Peso Argentino (ARS) => Dólar Americano (USD)");
        System.out.println("5) Dólar Americano (USD) => Euro (EUR)");
        System.out.println("6) Euro (EUR) => Real Brasileiro (BRL)");
        System.out.println("7) Sair");
        System.out.print("\nEscolha uma opção: ");
    }

    private static void realizarConversao(String moedaOrigem, String moedaDestino, Map<String, Double> taxas, Scanner leitura) {
        System.out.println("Digite o valor que deseja converter: ");
        try {
            double valorParaConverter = leitura.nextDouble();

            double taxaOrigem = taxas.get(moedaOrigem);
            double taxaDestino = taxas.get(moedaDestino);

            double valorConvertido = valorParaConverter * (taxaDestino / taxaOrigem);

            System.out.println("\nRESULTADO");
            System.out.printf("Valor de %.2f [%s] corresponde ao valor final de %.2f [%s]\n", valorParaConverter, moedaOrigem, valorConvertido, moedaDestino);

        } catch (InputMismatchException e) {
            System.out.println("Erro: Valor inválido. Por valor, digite um número.");
            leitura.next();
        }
    }
}
