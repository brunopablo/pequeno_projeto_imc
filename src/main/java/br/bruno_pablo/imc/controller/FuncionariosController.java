//primeira versao 22/09/2025

package br.bruno_pablo.imc.controller;

import java.util.List;

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

import br.bruno_pablo.imc.Dto.AtualizarFuncionarioDto;
import br.bruno_pablo.imc.Dto.CalcularImcDto;
import br.bruno_pablo.imc.Dto.CalcularImcResponse;
import br.bruno_pablo.imc.Dto.CriarOuAtualizarClienteDto;
import br.bruno_pablo.imc.Dto.CriarOuListarFuncionarioDto;
import br.bruno_pablo.imc.Dto.ListarFuncionarioResponse;
import br.bruno_pablo.imc.service.FuncionarioService;
import br.bruno_pablo.imc.service.InformacoesImcService;

@RestController
@RequestMapping("/v1/funcionarios")
public class FuncionariosController {

    private FuncionarioService funcionarioService;

    private InformacoesImcService informacoesImcService;

    public FuncionariosController(FuncionarioService funcionarioService,
            InformacoesImcService informacoesImcService) {
        this.funcionarioService = funcionarioService;
        this.informacoesImcService = informacoesImcService;
    }

    @PostMapping("/calcular_imc")
    public ResponseEntity<CalcularImcResponse> calcularImc(@RequestBody CalcularImcDto dadosCliente) {

        var classificacaoImcResponse = informacoesImcService.calcularImc(dadosCliente);

        return ResponseEntity.ok(classificacaoImcResponse);

    }

    @PostMapping("/cadastrar_funcionario")
    public ResponseEntity<String> cadastrarFuncionario(@RequestBody CriarOuListarFuncionarioDto dados_funcionario) {

        funcionarioService.cadastrarFuncionario(dados_funcionario);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario Criado Com Sucesso: " + dados_funcionario.nome_funcionario());
    }

    @PostMapping("/{funcionarioId}/cadastrar_cliente")
    public ResponseEntity<String> cadastrarCliente(@PathVariable String funcionarioId,
            @RequestBody CriarOuAtualizarClienteDto dados_Cliente) {

        funcionarioService.cadastrarCliente(funcionarioId, dados_Cliente);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario Criado Com Sucesso: " + dados_Cliente.nome_cliente());

    }

    @GetMapping("{funcionarioId}")
    public ResponseEntity<ListarFuncionarioResponse> listarFuncionarioPorId(@PathVariable String funcionarioId) {

        var funcionario = funcionarioService.listarFuncionarioPorId(funcionarioId);

        return ResponseEntity.ok(funcionario);

    }

    @GetMapping()
    public ResponseEntity<List<ListarFuncionarioResponse>> listarFuncionarios() {

        var funcionarios = funcionarioService.listarFuncionarios();

        return ResponseEntity.ok(funcionarios);

    }

    @PutMapping("/{idFuncionario}")
    public ResponseEntity<String> atualizarFuncionario(@PathVariable String idFuncionario,
            @RequestBody AtualizarFuncionarioDto dadosFuncionario) {

        boolean funcionarioEncontrado = funcionarioService.atualizarFuncionario(idFuncionario, dadosFuncionario);

        if (funcionarioEncontrado) {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    "Funcionario Atualizado");
        }

        // return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                "Funcionario Não Cadastrado");

    }

    @DeleteMapping("/{idFuncionario}")
    public ResponseEntity<String> deletarFuncionarioPorId(@PathVariable String idFuncionario) {

        boolean funcionarioEncontrado = funcionarioService.deletarFuncionario(idFuncionario);

        if (funcionarioEncontrado) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                "Funcionario Não Cadastrado");
    }
}
