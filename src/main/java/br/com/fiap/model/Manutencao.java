package br.com.fiap.model;

import java.sql.Date;

public class Manutencao {
    private int id;
    private Carro carro;
    private Date data;
    private String detalhes;
    private String descricao;

    // Construtor completo
    public Manutencao(int id, Carro carro, Date data, String detalhes, String descricao) {
        this.id = id;
        this.carro = carro;
        this.data = data;
        this.detalhes = detalhes;
        this.descricao = descricao;
    }

    // Construtor sem ID
    public Manutencao(Carro carro, Date data, String detalhes, String descricao) {
        this.carro = carro;
        this.data = data;
        this.detalhes = detalhes;
        this.descricao = descricao;
    }

    // Construtor com carroId
    public Manutencao(int id, String descricao, Date data, int carroId) {
        this.id = id;
        this.descricao = descricao;
        this.data = data;
        this.carro = new Carro(carroId); // Criar um novo carro com o carroId
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Retorna o ID do carro associado, se existir
    public int getCarroId() {
        return (carro != null) ? carro.getCarroId() : 0; // Usar carroId em vez de id
    }
}
