package br.bruno_pablo.imc.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.crypto.Data;

import org.springframework.stereotype.Service;

import br.bruno_pablo.imc.Dto.CalcularImcDto;
import br.bruno_pablo.imc.Dto.CalcularImcResponse;
import br.bruno_pablo.imc.Dto.ListarTodosImcsResponse;
import br.bruno_pablo.imc.Dto.InformacoesCompletasParaCalcularImcDto;
import br.bruno_pablo.imc.Dto.InformacoesIniciaisImcDto;
import br.bruno_pablo.imc.Dto.ListarInformacoesImcResponse;
import br.bruno_pablo.imc.Dto.ListarUnicoImcResponse;
import br.bruno_pablo.imc.Dto.request.AtualizarImcRequest;
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

    public String cadastrarInformacoesImcCliente(String idCliente,
            InformacoesIniciaisImcDto informacoesIniciaisCliente) {

        var clienteEntidade = clienteRepository.findById(UUID.fromString(idCliente))
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        String classificacao = calcularImcUtils
                .calcularClassificacaoImc(new InformacoesCompletasParaCalcularImcDto(
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

    public ListarTodosImcsResponse listarInformacoesImcClienteId(String idCliente) {

        var clienteEntidade = clienteRepository.findById(UUID.fromString(idCliente)).get();

        List<ListarInformacoesImcResponse> informacoesImc = clienteEntidade.getInformacoesImc().stream().map(
                infImcResponse -> new ListarInformacoesImcResponse(
                        infImcResponse.getDescricao(),
                        infImcResponse.getData(),
                        infImcResponse.getPeso(),
                        infImcResponse.getClassificacao()))
                .toList();

        return new ListarTodosImcsResponse(
                clienteEntidade.getNome_cliente(),
                informacoesImc);

    }

    public List<ListarTodosImcsResponse> listarTodosImcs() {

        var imcsEntidade = informacoesImcRepository.findAll();

        List<ListarTodosImcsResponse> imcResponse = imcsEntidade.stream().map(
                imcResponseObj -> {
                    List<ListarInformacoesImcResponse> listaInformacoesImc = new ArrayList<>();
                    listaInformacoesImc.add(new ListarInformacoesImcResponse(
                            imcResponseObj.getDescricao(),
                            imcResponseObj.getData(),
                            imcResponseObj.getPeso(),
                            imcResponseObj.getClassificacao()));

                    return new ListarTodosImcsResponse(imcResponseObj.getCliente().getNome_cliente(),
                            listaInformacoesImc);
                }).toList();

        return imcResponse;

    }

    public ListarUnicoImcResponse listarImcPorId(String idImc) {

        var imcResponse = informacoesImcRepository.findById(Integer.valueOf(idImc)).get();

        return new ListarUnicoImcResponse(
                imcResponse.getCliente().getNome_cliente(),
                new ListarInformacoesImcResponse(
                        imcResponse.getDescricao(),
                        imcResponse.getData(),
                        imcResponse.getPeso(),
                        imcResponse.getClassificacao()
                )
        );
    }

    public CalcularImcResponse calcularImc(CalcularImcDto dadosImc) {

        return calcularImcUtils.calcularImc(dadosImc);
    }

    public boolean deletarImc(int idImc) {


        if (checarImcCadastrado(idImc)) {
                informacoesImcRepository.deleteById(idImc);
                return true;
        }
        
        return false;
        
    }

    public boolean atualizarImc(int idCliente, AtualizarImcRequest novosDadosImc) {


        if (checarImcCadastrado(idCliente)) {
                var imcEntidade = informacoesImcRepository.findById(idCliente).get();

                if (!imcEntidade.getClassificacao().equals(novosDadosImc.classificacao())
                                && novosDadosImc.classificacao() != null) {
                        imcEntidade.setClassificacao(novosDadosImc.classificacao());
                }
                
                if (novosDadosImc.descricao() != null
                                && !novosDadosImc.descricao().equals(imcEntidade.getDescricao())) {
                        imcEntidade.setDescricao(novosDadosImc.descricao());
                }
                
                if (novosDadosImc.peso() != null && novosDadosImc.peso() != imcEntidade.getPeso()
                                && novosDadosImc.peso() > 0) {
                        imcEntidade.setPeso(novosDadosImc.peso());
                }
                
                imcEntidade.setData(LocalDate.now());

                informacoesImcRepository.save(imcEntidade);

                return true;
        }
        
        return false;
    }
    
    private boolean checarImcCadastrado(int idImc) {
        return informacoesImcRepository.existsById(idImc);
    }


}
