package br.bruno_pablo.imc.Dto;

import java.util.List;

public record ListarClienteResponse(
        String nome_cliente,
        double altura_cliente,
        int idade_cliente,
        String genero_cliente,
        String nome_funcionario,
        List<ListarInformacoesImcResponse> informacoes_Imc_cliente) {

}
