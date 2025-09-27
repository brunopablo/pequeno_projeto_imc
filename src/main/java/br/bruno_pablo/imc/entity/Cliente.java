package br.bruno_pablo.imc.entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_cliente;

    private String nome_cliente;

    private double altura_cliente;

    private int idade_cliente;

    private String genero_cliente;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @OneToMany(mappedBy = "cliente", cascade=CascadeType.REMOVE)
    private List<InformacoesImc> informacoesImc;

    public Cliente() {
    }

    public Cliente(double altura_cliente, Funcionario funcionario, String genero_cliente, UUID id_cliente,
            int idade_cliente, List<InformacoesImc> informacoesImc, String nome_cliente) {
        this.altura_cliente = altura_cliente;
        this.funcionario = funcionario;
        this.genero_cliente = genero_cliente;
        this.id_cliente = id_cliente;
        this.idade_cliente = idade_cliente;
        this.informacoesImc = informacoesImc;
        this.nome_cliente = nome_cliente;
    }

    public UUID getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(UUID id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public double getAltura_cliente() {
        return altura_cliente;
    }

    public void setAltura_cliente(double altura_cliente) {
        this.altura_cliente = altura_cliente;
    }

    public int getIdade_cliente() {
        return idade_cliente;
    }

    public void setIdade_cliente(int idade_cliente) {
        this.idade_cliente = idade_cliente;
    }

    public String getGenero_cliente() {
        return genero_cliente;
    }

    public void setGenero_cliente(String genero_cliente) {
        this.genero_cliente = genero_cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<InformacoesImc> getInformacoesImc() {
        return informacoesImc;
    }

    public void setInformacoesImc(List<InformacoesImc> informacoesImc) {
        this.informacoesImc = informacoesImc;
    }

}
