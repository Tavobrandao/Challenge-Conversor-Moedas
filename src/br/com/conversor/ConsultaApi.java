package br.com.conversor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaApi {
    public String obterDados(String moedaBase) {
        String apiKey = "623600319357986f6a384922";
        URI endereco = URI.create("https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + moedaBase);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(endereco).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Não foi possível obter s taxas de câmbio. Verifique sua conexão e a moeda informada.");
        }
    }
}
