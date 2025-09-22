//primeira versao 22/09/2025

package br.bruno_pablo.imc.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.bruno_pablo.imc.Dto.CriarClienteDto;
import br.bruno_pablo.imc.Dto.CriarOuListarFuncionarioDto;
import br.bruno_pablo.imc.Dto.ListarClienteResponse;
import br.bruno_pablo.imc.Dto.ListarFuncionarioResponse;
import br.bruno_pablo.imc.Dto.InformacoesIniciaisImcDto;
import br.bruno_pablo.imc.service.ClienteService;
import br.bruno_pablo.imc.service.FuncionarioService;
import br.bruno_pablo.imc.service.InformacoesImcService;

@RestController
@RequestMapping("/v1")
public class ImcController {

    private FuncionarioService funcionarioService;

    private ClienteService clienteService;

    private InformacoesImcService informacoesImcService;

    public ImcController(FuncionarioService funcionarioService, ClienteService clienteService,
            InformacoesImcService informacoesImcService) {
        this.funcionarioService = funcionarioService;
        this.clienteService = clienteService;
        this.informacoesImcService = informacoesImcService;
    }

    @PostMapping("/criar_funcionario")
    public ResponseEntity<String> criarFuncionario(@RequestBody CriarOuListarFuncionarioDto dados_funcionario) {

        funcionarioService.criarFuncionario(dados_funcionario);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario Criado Com Sucesso: " + dados_funcionario.nome_funcionario());
    }

    @PostMapping("/criar_cliente/{funcionarioId}")
    public ResponseEntity<String> criarUsuario(@PathVariable String funcionarioId,
            @RequestBody CriarClienteDto dados_Cliente) {

        funcionarioService.criarCliente(funcionarioId, dados_Cliente);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario Criado Com Sucesso: " + dados_Cliente.nome_cliente());

    }

    @GetMapping("/listar_funcionario/{funcionarioId}")
    public ResponseEntity<ListarFuncionarioResponse> listarFuncionarioPorId(@PathVariable String funcionarioId) {

        var funcionario = funcionarioService.listarFuncionarioPorId(funcionarioId);

        return ResponseEntity.ok(funcionario);

    }

    @GetMapping("/listar_funcionarios")
    public ResponseEntity<List<ListarFuncionarioResponse>> listarFuncionarios() {

        var funcionarios = funcionarioService.listarFuncionarios();

        return ResponseEntity.ok(funcionarios);

    }

    @GetMapping("/listar_clientes")
    public ResponseEntity<List<ListarClienteResponse>> listarClientes() {

        var clientes = clienteService.listarClientes();

        return ResponseEntity.ok(clientes);

    }

    @GetMapping("/listar_cliente/{idCliente}")
    public ResponseEntity<ListarClienteResponse> listarClientePorId(@PathVariable String idCliente) {

        var cliente = clienteService.listarClientePorId(idCliente);

        return ResponseEntity.ok(cliente);

    }

    @PostMapping("/inserirInformacoesImc/{idCliente}")
    public ResponseEntity<String> inserirInformacoesImcCliente(@PathVariable String idCliente,
            @RequestBody InformacoesIniciaisImcDto dadosIniciaisImc) {

        String nomeCliente = informacoesImcService.inserirInformacoesImcCliente(idCliente, dadosIniciaisImc);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                "Informacoes de Imc Inseridas Com Sucesso do Usuário " + nomeCliente);

    }
}
