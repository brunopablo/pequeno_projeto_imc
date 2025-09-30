package br.bruno_pablo.imc.Dto;


public record CriarOuAtualizarClienteDto(String nome_cliente,
        Double altura_cliente,
        Integer idade_cliente,
        String genero_cliente) {

}
