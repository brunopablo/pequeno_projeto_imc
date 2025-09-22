package br.bruno_pablo.imc.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.bruno_pablo.imc.Dto.ListarClienteResponse;
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
                clienteEntidade.getFuncionario().getNome());
    }

    public List<ListarClienteResponse> listarClientes() {
        var clientesEntidade = clienteRepository.findAll();

        return clientesEntidade.stream().map(
                cli -> new ListarClienteResponse(
                        cli.getNome_cliente(),
                        cli.getAltura_cliente(),
                        cli.getIdade_cliente(),
                        cli.getGenero_cliente(),
                        cli.getFuncionario().getNome()))
                .toList();

    }

}
