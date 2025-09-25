package br.bruno_pablo.imc.utils;

import org.springframework.stereotype.Component;

import br.bruno_pablo.imc.Dto.CalcularImcDto;
import br.bruno_pablo.imc.Dto.CalcularImcResponse;
import br.bruno_pablo.imc.Dto.InformacoesCompletasParaCalcularImcDto;

@Component
public class CalcularImcUtils {

    public String calcularClassificacaoImc(InformacoesCompletasParaCalcularImcDto dadosImcCliente) {

        // IMC = peso / (altura x altura).

        // Menor que 18,5 Magreza
        // 18,5 a 24,9 Normal
        // 25 a 29,9 Sobrepeso
        // 30 a 34,9 Obesidade grau I
        // 35 a 39,9 Obesidade grau II
        // Maior que 40 Obesidade grau III

        double alturaCLiente = dadosImcCliente.alturaCliente();

        double imc = dadosImcCliente.informacoesIniciaisImc().peso() / (alturaCLiente * alturaCLiente);

        System.out.println(imc);

        if (imc < 18.5) {
            return "Magreza";
        }
        if (imc > 40) {
            return "Obesidade grau III";
        }
        if (imc < 24.9) {
            return "Normal";
        }
        if (imc < 29.9) {
            return "Sobrepeso";
        }
        if (imc < 34.9) {
            return "Obesidade grau I";
        }
        if (imc < 39.9) {
            return "Obesidade grau II";
        }

        return "ERRO AO CALCULAR IMC";
    }

    public CalcularImcResponse calcularImc(CalcularImcDto dadosImc) {
        Double imc = dadosImc.peso() / (dadosImc.altura() * dadosImc.altura());

        // pode ser melhorado para switch patterns
        if (imc < 18.5) {
            return RetornarImcResponseRotina(dadosImc, imc, "Magreza");
        }
        if (imc > 40) {
            return RetornarImcResponseRotina(dadosImc, imc, "Obesidade Grau III");
        }
        if (imc < 24.9) {
            return RetornarImcResponseRotina(dadosImc, imc, "Normal");
        }
        if (imc < 29.9) {
            return RetornarImcResponseRotina(dadosImc, imc, "Sobrepeso");
        }
        if (imc < 34.9) {
            return RetornarImcResponseRotina(dadosImc, imc, "Obesidade Grau I");
        }
        if (imc < 39.9) {
            return RetornarImcResponseRotina(dadosImc, imc, "Obesidade Grau II");
        }

        return null;
    }

    private CalcularImcResponse RetornarImcResponseRotina(CalcularImcDto dadosImc, Double imc, String classificacao) {
        return new CalcularImcResponse(
                dadosImc.nome(),
                dadosImc.altura(),
                dadosImc.peso(),
                String.format("%.2f", imc),
                classificacao);
    }
}
