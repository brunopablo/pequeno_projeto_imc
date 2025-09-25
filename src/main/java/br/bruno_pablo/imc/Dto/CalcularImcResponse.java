package br.bruno_pablo.imc.Dto;

public record CalcularImcResponse(String nome, Double altura, Double peso, String imc, String classificacaoImc) {

}
