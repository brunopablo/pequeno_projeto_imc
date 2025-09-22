package br.bruno_pablo.imc.Dto;

public record ListarClienteResponse(
                String nome_cliente,
                double altura_cliente,
                int idade_cliente,
                String genero_cliente,
                String nome_funcionario) {

}
