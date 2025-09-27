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

import br.bruno_pablo.imc.Dto.InformacoesIniciaisImcDto;
import br.bruno_pablo.imc.Dto.ListarClienteResponse;
import br.bruno_pablo.imc.Dto.ListarTodosImcsResponse;
import br.bruno_pablo.imc.Dto.ListarUnicoImcResponse;
import br.bruno_pablo.imc.service.ClienteService;
import br.bruno_pablo.imc.service.InformacoesImcService;

@RestController
@RequestMapping("/v1/clientes")
public class ClientesController {

    private ClienteService clientesService;
    private InformacoesImcService informacoesImcService;

    public ClientesController(ClienteService clientesService, InformacoesImcService informacoesImcService) {
        this.clientesService = clientesService;
        this.informacoesImcService = informacoesImcService;
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

    
    @GetMapping("/imcs")
    public ResponseEntity<List<ListarTodosImcsResponse>> listarImcs() {
        
        var imcsResponse = informacoesImcService.listarTodosImcs();


        return ResponseEntity.ok(imcsResponse);
    }


    @GetMapping("/imcs/{idImc}")
    public ResponseEntity<ListarUnicoImcResponse> listarImcPorId(@PathVariable String idImc){

        var imcResponse = informacoesImcService.listarImcPorId(idImc);

        return ResponseEntity.ok(imcResponse);

    }


    @PostMapping("{idCliente}/cadastrar_imc")
    public ResponseEntity<String> cadastrarImcCliente(@PathVariable String idCliente,
            @RequestBody InformacoesIniciaisImcDto informacoesImc) {

        var clienteNome = informacoesImcService.inserirInformacoesImcCliente(idCliente, informacoesImc);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                "Informações IMC cadastradas no cliente: " + clienteNome);
    }
    
}

