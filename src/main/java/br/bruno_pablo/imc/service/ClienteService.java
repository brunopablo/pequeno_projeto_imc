package br.bruno_pablo.imc.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.bruno_pablo.imc.Dto.ListarClienteResponse;
import br.bruno_pablo.imc.Dto.ListarInformacoesImcResponse;
import br.bruno_pablo.imc.repository.ClienteRepository;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ListarClienteResponse listarClientePorId(String idCliente) {

        var clienteEntidade = clienteRepository.findById(UUID.fromString(idCliente)).get();

        return new ListarClienteResponse(
                clienteEntidade.getNome_cliente(),
                clienteEntidade.getAltura_cliente(),
                clienteEntidade.getIdade_cliente(),
                clienteEntidade.getGenero_cliente(),
                clienteEntidade.getFuncionario().getNome(),
                clienteEntidade.getInformacoesImc().stream().map(
                        infImcResponse -> new ListarInformacoesImcResponse(
                                infImcResponse.getDescricao(),
                                infImcResponse.getData(),
                                infImcResponse.getPeso(),
                                infImcResponse.getClassificacao()))
                        .toList());
    }

    public List<ListarClienteResponse> listarClientes() {
        var clientesEntidade = clienteRepository.findAll();

        return clientesEntidade.stream().map(
                cliResponse -> new ListarClienteResponse(
                        cliResponse.getNome_cliente(),
                        cliResponse.getAltura_cliente(),
                        cliResponse.getIdade_cliente(),
                        cliResponse.getGenero_cliente(),
                        cliResponse.getFuncionario().getNome(),
                        cliResponse.getInformacoesImc().stream().map(
                                infImcResponse -> new ListarInformacoesImcResponse(
                                        infImcResponse.getDescricao(),
                                        infImcResponse.getData(),
                                        infImcResponse.getPeso(),
                                        infImcResponse.getClassificacao()))
                                .toList()))
                .toList();

    }

}
