package br.bruno_pablo.imc.Dto;

public record ListarClientesResponse(
                String nome_cliente,
                double altura_cliente,
                int idade_cliente,
                String genero_cliente) {

}
