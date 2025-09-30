package br.bruno_pablo.imc.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.bruno_pablo.imc.Dto.CalcularImcDto;
import br.bruno_pablo.imc.Dto.CalcularImcResponse;
import br.bruno_pablo.imc.Dto.ListarTodosImcsResponse;
import br.bruno_pablo.imc.Dto.ListarUnicoImcResponse;
import br.bruno_pablo.imc.service.InformacoesImcService;

@RestController
@RequestMapping("v1/clientes/imcs")
public class ImcsController {

    private InformacoesImcService informacoesImcService;

    public ImcsController(InformacoesImcService informacoesImcService) {
        this.informacoesImcService = informacoesImcService;
    }

    @PostMapping("/calcular_imc")
    public ResponseEntity<CalcularImcResponse> calcularImc(@RequestBody CalcularImcDto dadosImc) {

        var imcResponse = informacoesImcService.calcularImc(dadosImc);

        return ResponseEntity.ok(imcResponse);

    }

    @GetMapping()
    public ResponseEntity<List<ListarTodosImcsResponse>> listarImcs() {

        var imcsResponse = informacoesImcService.listarTodosImcs();

        return ResponseEntity.ok(imcsResponse);
    }

    @GetMapping("/imcs/{idImc}")
    public ResponseEntity<ListarUnicoImcResponse> listarImcPorId(@PathVariable String idImc) {

        var imcResponse = informacoesImcService.listarImcPorId(idImc);

        return ResponseEntity.ok(imcResponse);

    }
}
