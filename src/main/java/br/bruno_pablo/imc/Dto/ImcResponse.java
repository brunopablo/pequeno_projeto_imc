package br.bruno_pablo.imc.Dto;

import java.util.List;

public record ImcResponse(
        String nomeCLiente,
        List<ListarInformacoesImcResponse> informacoesImc) {

}
