package br.bruno_pablo.imc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.bruno_pablo.imc.Dto.CriarClienteDto;
import br.bruno_pablo.imc.Dto.CriarOuListarFuncionarioDto;
import br.bruno_pablo.imc.Dto.ListarClienteResponse;
import br.bruno_pablo.imc.Dto.ListarFuncionarioResponse;
import br.bruno_pablo.imc.Dto.ListarInformacoesImcResponse;
import br.bruno_pablo.imc.entity.Cliente;
import br.bruno_pablo.imc.entity.Funcionario;
import br.bruno_pablo.imc.repository.ClienteRepository;
import br.bruno_pablo.imc.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

        private FuncionarioRepository funcionarioRepository;
        private ClienteRepository clienteRepository;

        public FuncionarioService(FuncionarioRepository funcionarioRepository, ClienteRepository clienteRepository) {
                this.funcionarioRepository = funcionarioRepository;
                this.clienteRepository = clienteRepository;
        }

        public void criarFuncionario(CriarOuListarFuncionarioDto dadosFuncionario) {

                var funcionarioEntidade = new Funcionario(
                                null,
                                dadosFuncionario.nome_funcionario(),
                                new ArrayList<>());

                funcionarioRepository.save(funcionarioEntidade);

        }

        public void criarCliente(String funcionarioId, CriarClienteDto dadosCliente) {
                var funcionarioEntidade = funcionarioRepository.findById(UUID.fromString(funcionarioId))
                                .orElseThrow(() -> new RuntimeException("Funcionario nao encontrado"));

                var usuarioEntidade = new Cliente(
                                dadosCliente.altura_cliente(),
                                funcionarioEntidade,
                                dadosCliente.genero_cliente(),
                                null,
                                dadosCliente.idade_cliente(),
                                new ArrayList<>(),
                                dadosCliente.nome_cliente());

                clienteRepository.save(usuarioEntidade);

        }

        public ListarFuncionarioResponse listarFuncionarioPorId(String idFuncionario) {

                var funcionarioEntidade = funcionarioRepository.findById(UUID.fromString(idFuncionario)).orElseThrow(
                                () -> new RuntimeException("Funcionario Não Encontrado"));

                List<ListarClienteResponse> dadosCliente = funcionarioEntidade.getCliente().stream().map(
                                c -> new ListarClienteResponse(
                                                c.getNome_cliente(),
                                                c.getAltura_cliente(),
                                                c.getIdade_cliente(),
                                                c.getGenero_cliente(),
                                                c.getFuncionario().getNome(),
                                                c.getInformacoesImc().stream().map(
                                                                i -> new ListarInformacoesImcResponse(
                                                                                i.getDescricao(),
                                                                                i.getData(),
                                                                                i.getPeso(),
                                                                                i.getClassificacao()
                                                                )
                                                ).toList()
                                )
                ).toList();

                return new ListarFuncionarioResponse(
                                funcionarioEntidade.getNome(),
                                dadosCliente);
        }

        public List<ListarFuncionarioResponse> listarFuncionarios() {

                var funcionariosEntidade = funcionarioRepository.findAll();

                return funcionariosEntidade.stream().map(
                                f -> new ListarFuncionarioResponse(
                                                f.getNome(),
                                                f.getCliente().stream().map(
                                                                c -> new ListarClienteResponse(
                                                                                c.getNome_cliente(),
                                                                                c.getAltura_cliente(),
                                                                                c.getIdade_cliente(),
                                                                                c.getGenero_cliente(),
                                                                                c.getFuncionario().getNome(),
                                                                                c.getInformacoesImc().stream().map(
                                                                                                i -> new ListarInformacoesImcResponse(
                                                                                                                i.getDescricao(),
                                                                                                                i.getData(),
                                                                                                                i.getPeso(),
                                                                                                                i.getClassificacao())
                                                                                ).toList()
                                                                )
                                                ).toList()
                                )
                        ).toList();

        }

}
