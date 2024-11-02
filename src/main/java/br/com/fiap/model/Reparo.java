package br.com.fiap.model;

public class Reparo {
    private int id;
    private int carroId; // Campo carroId
    private Carro carro; // Objeto Carro
    private String problema;
    private String status;

    // Construtores
    public Reparo(int id, int carroId, String problema, String status) {
        this.id = id;
        this.carroId = carroId;
        this.problema = problema;
        this.status = status;
    }

    public Reparo(Carro carro, String problema, String status) {
        this.carro = carro;
        this.carroId = carro != null ? carro.getId() : 0; // Atribui o ID do carro, se não for nulo
        this.problema = problema;
        this.status = status;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarroId() {
        return carroId;
    }

    public void setCarroId(int carroId) {
        this.carroId = carroId;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
        this.carroId = carro != null ? carro.getId() : 0; // Atualiza carroId ao alterar o carro
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}