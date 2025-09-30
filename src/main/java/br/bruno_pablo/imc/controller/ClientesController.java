package br.bruno_pablo.imc.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.bruno_pablo.imc.Dto.CriarOuAtualizarClienteDto;
import br.bruno_pablo.imc.Dto.InformacoesIniciaisImcDto;
import br.bruno_pablo.imc.Dto.ListarClienteResponse;
import br.bruno_pablo.imc.service.ClienteService;
import br.bruno_pablo.imc.service.InformacoesImcService;
import br.bruno_pablo.imc.utils.ChecarDadosInvalidos;

@RestController
@RequestMapping("/v1/clientes")
public class ClientesController {

    private ClienteService clientesService;
    private InformacoesImcService informacoesImcService;
    private ChecarDadosInvalidos checarDadosInvalidos;

    public ClientesController(ClienteService clientesService, InformacoesImcService informacoesImcService, ChecarDadosInvalidos checarDadosInvalidos) {
        this.clientesService = clientesService;
        this.informacoesImcService = informacoesImcService;
        this.checarDadosInvalidos = checarDadosInvalidos;
    }

    @GetMapping
    public ResponseEntity<List<ListarClienteResponse>> listarClientes() {
        var clientesResponse = clientesService.listarClientes();

        return ResponseEntity.ok(clientesResponse);
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<ListarClienteResponse> listarClientePorId(@PathVariable String idCliente) {

        var clienteResponse = clientesService.listarClientePorId(idCliente);

        return ResponseEntity.ok(clienteResponse);

    }

    @PostMapping("{idCliente}/cadastrar_imc")
    public ResponseEntity<String> cadastrarImcCliente(@PathVariable String idCliente,
            @RequestBody InformacoesIniciaisImcDto informacoesImc) {

        var clienteNome = informacoesImcService.inserirInformacoesImcCliente(idCliente, informacoesImc);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                "Informações IMC cadastradas no cliente: " + clienteNome);
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<String> deletarCliente(@PathVariable String idCliente) {

        if (checarDadosInvalidos.uuidInvalido(idCliente)) {
            return ResponseEntity.badRequest().body("UUID INVALIDO!");

        }
        
        boolean deletarClienteResponse = clientesService.deletarCliente(UUID.fromString(idCliente));

        if (deletarClienteResponse) {
            return ResponseEntity.noContent().build();
        }

        
        return ResponseEntity.badRequest().body("Cliente Nao Existe");

    }

    @PutMapping("{idCliente}")
    public ResponseEntity<String> atualizarCliente(@PathVariable String idCliente, @RequestBody CriarOuAtualizarClienteDto novosDadosCliente) {

        String dadosInvalidos = checarDadosInvalidos.detectarNulos(novosDadosCliente);

        if (dadosInvalidos.isEmpty()) {
            clientesService.atualizarCliente(UUID.fromString(idCliente), novosDadosCliente);
            return ResponseEntity.created(URI.create(idCliente)).build();
        }

        return ResponseEntity.badRequest().body(dadosInvalidos);

    }

}
