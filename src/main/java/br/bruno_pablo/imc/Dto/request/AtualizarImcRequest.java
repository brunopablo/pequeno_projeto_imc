package br.bruno_pablo.imc.Dto.request;

public record AtualizarImcRequest(
        String classificacao,
        String descricao,
        Double peso) {

}
