package br.bruno_pablo.imc.Dto;

import java.util.List;


public record ListarFuncionarioResponse(
        String nome_funcionario,
        List<ListarClientesResponse> dados_clientes) {

}
