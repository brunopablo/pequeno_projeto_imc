package br.bruno_pablo.imc.service;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.bruno_pablo.imc.Dto.InformacoesCompletasParaCalcularImcDto;
import br.bruno_pablo.imc.Dto.InformacoesIniciaisImcDto;
import br.bruno_pablo.imc.entity.InformacoesImc;
import br.bruno_pablo.imc.repository.ClienteRepository;
import br.bruno_pablo.imc.repository.InformacoesImcRepository;
import br.bruno_pablo.imc.utils.CalcularImcUtils;

@Service
public class InformacoesImcService {

    private ClienteRepository clienteRepository;

    private InformacoesImcRepository informacoesImcRepository;

    private CalcularImcUtils calcularImcUtils;

    public InformacoesImcService(ClienteRepository clienteRepository, CalcularImcUtils calcularImcUtils,
            InformacoesImcRepository informacoesImcRepository) {
        this.clienteRepository = clienteRepository;
        this.calcularImcUtils = calcularImcUtils;
        this.informacoesImcRepository = informacoesImcRepository;
    }

    public String inserirInformacoesImcCliente(String idCliente, InformacoesIniciaisImcDto informacoesIniciaisCliente) {

        var clienteEntidade = clienteRepository.findById(UUID.fromString(idCliente))
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        String classificacao = calcularImcUtils.calcularClassificacaoImc(new InformacoesCompletasParaCalcularImcDto(
                        informacoesIniciaisCliente,
                        clienteEntidade.getAltura_cliente(),
                        clienteEntidade.getIdade_cliente()));

        var informacoesImc = new InformacoesImc(
                classificacao,
                clienteEntidade,
                LocalDate.now(),
                informacoesIniciaisCliente.descricao(),
                null,
                informacoesIniciaisCliente.peso());

        informacoesImcRepository.save(informacoesImc);

        return clienteEntidade.getNome_cliente();
    }

}
