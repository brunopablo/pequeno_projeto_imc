package br.bruno_pablo.imc.utils;


import java.util.UUID;

import org.springframework.stereotype.Component;

import br.bruno_pablo.imc.Dto.CriarOuAtualizarClienteDto;


@Component
public class ChecarDadosInvalidos {

    public String detectarNulos(CriarOuAtualizarClienteDto novosDadosCliente) {
        String dadosInvalidos = "";

        if (novosDadosCliente.nome_cliente().isEmpty() || novosDadosCliente.nome_cliente() == null) {
            dadosInvalidos += "Campo Nome está invalido!";
        }

        if (novosDadosCliente.altura_cliente() == null) {
            dadosInvalidos += "Campo Altura está invalido!";
        }

        if (novosDadosCliente.genero_cliente().isEmpty() || novosDadosCliente.nome_cliente() == null) {
            dadosInvalidos += "Campo Genero está invalido";
        }

        if (novosDadosCliente.idade_cliente() == null) {
            dadosInvalidos += "Campo Idade está invalido";
        }

        return dadosInvalidos;

    }


    public boolean uuidInvalido(String id) {
        
        try {
            
            UUID.fromString(id);

            return false;

        } catch (Exception e) {
            return true;
        }
    }
}
