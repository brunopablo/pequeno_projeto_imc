package br.bruno_pablo.imc.Dto;

import java.time.LocalDate;

public record ListarInformacoesImcResponse(
        String descricaoImc,
        LocalDate dataImc,
        Double pesoImc,
        String classificacaoImc) {

}
