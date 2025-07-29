package br.com.conversor;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Representa a estrutura da resposta JSON da ExchangeRate-API.
 * Utiliza um Record do Java para criar uma classe de dados imutável e concisa.
 * Os campos deste record correspondem às chaves do JSON retornado pela API.
 *
 * @param result O status da requisição (ex: "success").
 * @param codigoMoedaBase O código da moeda que foi usada como base na requisição (ex: "USD").
 * @param taxasDeConversao Um mapa contendo os códigos das moedas e suas respectivas taxas de câmbio.
 */
public record MoedaApiResponse(String result,
                               // A anotação @SerialzedName é usada para mapear chaves do JSON que não seguem nomenclatura do java (camelCase).
                               // JSON "base_code" -> Java "codigoMoedaBase".
                               @SerializedName("base_code") String codigoMoedaBase,

                               // JSON "conversion_rates" -> Java "taxasDeConversao"
                               @SerializedName("conversion_rates")
Map<String, Double> taxasDeConversao) {}
