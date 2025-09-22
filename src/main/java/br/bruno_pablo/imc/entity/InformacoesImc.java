package br.bruno_pablo.imc.entity;


import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_informacoesImc")
public class InformacoesImc {
    

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private LocalDate data; // ano mes dia

    private String descricao;

    private Double peso;

    private String classificacao;

    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

    public InformacoesImc() {
    }

    public InformacoesImc(String classificacao, Cliente cliente, LocalDate data, String descricao, Integer id, Double peso) {
        this.classificacao = classificacao;
        this.cliente = cliente;
        this.data = data;
        this.descricao = descricao;
        this.id = id;
        this.peso = peso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }





}
