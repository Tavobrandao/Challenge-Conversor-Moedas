package br.com.conversor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Classe responsável por encapsular a lógica de comunicação com a ExchangeRate-API.
 * Ela constrói a requisição, envia para a API e retorna a resposta como uma String JSON.
 */
public class ConsultaApi {
    /**
     * Realiza uma requisição GET para a ExchangeRate-API para obter as taxas de câmbio.
     * @param moedaBase O código da moeda a ser usada como base para as taxas de conversão (ex: "USD").
     * @return Uma String contendo o corpo da resposta em formato JSON.
     * @throws RuntimeException Se ocorrer um erro de rede ou interrupção durante a chamada.
     */
    public String obterDados(String moedaBase) {
        // Chave pessoal obtida no site da API.
        String apiKey = "623600319357986f6a384922";
        URI endereco = URI.create("https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + moedaBase);

        // Cria um cliente HTTP para fazer as requisições.
        HttpClient client = HttpClient.newHttpClient();

        // Constrói uma requisição HTTP do tipo GET para o endereço da API.
        HttpRequest request = HttpRequest.newBuilder().uri(endereco).build();

        try {
            // Envia a requisição e obtém a resposta.
            // O corpo da resposta é tratado como uma String (HttpResponse.BodyHandlers.ofString()).
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body(); // Retorna a String JSON.
        } catch (IOException | InterruptedException e) {
            // Captura exceções de rede (IOException) ou de interrupção da thread (InterruptedException).
            // Lança uma exceção de runtime para sinalizar o erro à classe que chamou o método.
            throw new RuntimeException("Não foi possível obter s taxas de câmbio. Verifique sua conexão e a moeda informada.");
        }
    }
}
