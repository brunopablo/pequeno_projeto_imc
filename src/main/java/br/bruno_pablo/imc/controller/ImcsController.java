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

import br.bruno_pablo.imc.Dto.CalcularImcDto;
import br.bruno_pablo.imc.Dto.CalcularImcResponse;
import br.bruno_pablo.imc.Dto.ListarTodosImcsResponse;
import br.bruno_pablo.imc.Dto.ListarUnicoImcResponse;
import br.bruno_pablo.imc.Dto.request.AtualizarImcRequest;
import br.bruno_pablo.imc.service.InformacoesImcService;
import br.bruno_pablo.imc.utils.ChecarDadosInvalidos;

@RestController
@RequestMapping("v1/clientes/imcs")
public class ImcsController {

    private InformacoesImcService informacoesImcService;
    private ChecarDadosInvalidos checarDadosInvalidos;

    public ImcsController(InformacoesImcService informacoesImcService, ChecarDadosInvalidos checarDadosInvalidos) {
        this.informacoesImcService = informacoesImcService;
        this.checarDadosInvalidos = checarDadosInvalidos;
    }

    @PostMapping("/calcular_imc_sem_persistencia")
    public ResponseEntity<CalcularImcResponse> calcularImc(@RequestBody CalcularImcDto dadosImc) {

        var imcResponse = informacoesImcService.calcularImc(dadosImc);

        return ResponseEntity.ok(imcResponse);

    }

    @GetMapping()
    public ResponseEntity<List<ListarTodosImcsResponse>> listarImcs() {

        var imcsResponse = informacoesImcService.listarTodosImcs();

        return ResponseEntity.ok(imcsResponse);
    }

    @GetMapping("/{idImc}")
    public ResponseEntity<ListarUnicoImcResponse> listarImcPorId(@PathVariable String idImc) {

        var imcResponse = informacoesImcService.listarImcPorId(idImc);

        return ResponseEntity.ok(imcResponse);

    }


    @DeleteMapping("/{idImc}")
    public ResponseEntity<String> deletarImc(@PathVariable String idImc) {

        boolean operacaoConcluida = informacoesImcService.deletarImc(Integer.parseInt(idImc));

        if (operacaoConcluida) {

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.badRequest().body("Imc não cadastrado!");

    }
    

    @PutMapping("/{idImc}")
    public ResponseEntity<String> atualizarImc(@PathVariable String idImc, @RequestBody AtualizarImcRequest novosDadosImc){

        boolean operacaoConcluida = informacoesImcService.atualizarImc(Integer.parseInt(idImc), novosDadosImc);

        System.out.println(Integer.parseInt(idImc));

        if (operacaoConcluida) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Imc Atualizado");
        }

        return ResponseEntity.badRequest().body("Imc não cadastrado!");

    }
}
