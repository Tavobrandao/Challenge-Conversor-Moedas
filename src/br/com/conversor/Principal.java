package br.com.conversor;

import com.google.gson.Gson;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

/**
 * Classe principal da aplicação de conversão de moedas.
 * Responsável por interagir com o usuário, exibir o menu,
 * coletar as entradas e orquestrar a chamada à API e a exibição dos resultados.
 */
public class Principal {
    public static void main(String[] args) {
        // Inicialização de objetos essenciais para a aplicação.
        Scanner leitura = new Scanner(System.in); // Objeto para ler a entrada do usuário no console.
        ConsultaApi consulta = new ConsultaApi(); // Objeto que encapsula a lógica de chamada à API.
        Gson gson = new Gson(); // Objeto para converter o JSON em objeto Java.

        // Chamada Única à API
        // Para otimizar, obtemos todas as taxas de conversão uma única vez no início.
        // Usamos USD como moeda base por ser uma referência comum e abrangente.
        String jsonResposta = consulta.obterDados("USD");
        MoedaApiResponse resposta = gson.fromJson(jsonResposta, MoedaApiResponse.class);
        Map<String, Double> taxas = resposta.taxasDeConversao();

        // Loop principal da aplicação. Continua executando até que o usuário escolha sair.
        while (true) {
            exibirMenu(); // Apresenta as opções ao usuário.
            try {
                int opcao = leitura.nextInt(); // Lê a opção numérica do usuário.

                // Condição de saída do loop.
                if (opcao == 7) {
                    System.out.println("\nObrigado por usar o Conversor de Moedas. Até logo!");
                    break; // Encerra o loop e, consequentemente, o programa.
                }

                String moedaOrigem = "";
                String moedaDestino = "";

                // O switch direciona o fluxo com base na escolha do usuário.
                // Define as moedas de origem e destino para cada opção.
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
                        continue; // Pula o resto do loop e volta para a exibição do menu.
                }

                // Delega a lógica de conversão para um método separado.
                realizarConversao(moedaOrigem, moedaDestino, taxas, leitura);

            } catch (InputMismatchException e) {
                // Tratamento de erro caso o usuário digite algo que não seja um número inteiro.
                System.out.println("Erro: Por favor, digite um número válido para a opção.");
                leitura.next(); // Limpa o buffer do scanner para evitar um loop infinito de erro.
            }
        }
        leitura.close(); // Fecha o Scanner ao final da execução para liberar recursos.
    }

    /**
     * Exibe o menu principal de opções para o usuário.
     */
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

    /**
     * Realiza a lógica de conversão de moeda.
     * Pede o valor ao usuário, calcula e exibe o resultado formatado.
     * @param moedaOrigem O código da moeda de origem (ex: "USD").
     * @param moedaDestino O código da moeda de destino (ex: "BRL").
     * @param taxas O mapa contendo todas as taxas de conversão relativas à moeda base.
     * @param leitura O objeto Scanner para ler a entrada do usuário.
     */
    private static void realizarConversao(String moedaOrigem, String moedaDestino, Map<String, Double> taxas, Scanner leitura) {
        System.out.println("Digite o valor que deseja converter: ");
        try {
            double valorParaConverter = leitura.nextDouble();

            // Busca as taxas de conversão para as moedas de origem e destino no mapa.
            double taxaOrigem = taxas.get(moedaOrigem);
            double taxaDestino = taxas.get(moedaDestino);

            // Fórmula de conversão cruzada: converte o valor para a moeda base (USD) e depois para a moeda destino.
            // (valor / taxaOrigem) -> converte para USD.
            // (...) * taxaDestino -> converte de USD para a moeda destino.
            double valorConvertido = valorParaConverter * (taxaDestino / taxaOrigem);

            // Exibe o resultado formatado com duas casas decimais para representar valores monetários.
            // %.2f -> formata um double/float com 2 casas decimais.
            // %s -> formata uma string.
            // \n -> nova linha.
            System.out.println("\nRESULTADO");
            System.out.printf("Valor de %.2f [%s] corresponde ao valor final de %.2f [%s]\n", valorParaConverter, moedaOrigem, valorConvertido, moedaDestino);

        } catch (InputMismatchException e) {
            // Tratamento de erro caso o usuário digite um valor não numérico.
            System.out.println("Erro: Valor inválido. Por valor, digite um número.");
            leitura.next(); // Limpa o buffer do scanner.
        }
    }
}
