package br.com.conversor;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public record MoedaApiResponse(String result, @SerializedName("base_code") String codigoMoedaBase, @SerializedName("conversion_rates")
Map<String, Double> taxasDeConversao) {}
