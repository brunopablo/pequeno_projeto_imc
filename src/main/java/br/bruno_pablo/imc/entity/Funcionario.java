package br.bruno_pablo.imc.entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_funcionario;

    private String nome;

    @OneToMany(mappedBy = "funcionario", cascade=CascadeType.REMOVE)
    private List<Cliente> cliente;

    public Funcionario() {
    }

    public Funcionario(UUID id_funcionario, String nome, List<Cliente> cliente) {
        this.cliente = cliente;
        this.id_funcionario = id_funcionario;
        this.nome = nome;
    }

    public UUID getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(UUID id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Cliente> getCliente() {
        return cliente;
    }

    public void setCliente(List<Cliente> cliente) {
        this.cliente = cliente;
    }

}
