package br.bruno_pablo.imc.Dto;

import java.util.List;

public record ListarTodosImcsResponse(
        String nomeCLiente,
        List<ListarInformacoesImcResponse> informacoesImc) {

}
