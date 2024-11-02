package br.com.fiap.model;

public class Carro {
    private int id;
    private int carroId;
    private String placa;
    private String modelo;
    private String marca;

    // Construtor com todos os atributos
    public Carro(int id, String placa, String modelo, String marca) {
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
    }

    // Construtor sem ID
    public Carro(String placa, String modelo, String marca) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
    }

    // Construtor com carroId
    public Carro(int carroId) {
        this.carroId = carroId;
    }

    // Getters e setters
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa; // Adicionar validação se necessário
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo; // Adicionar validação se necessário
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca; // Adicionar validação se necessário
    }

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
        this.carroId = carroId; // Adicionar validação se necessário
    }

    @Override
    public String toString() {
        return "Carro{" +
                "id=" + id +
                ", carroId=" + carroId +
                ", placa='" + placa + '\'' +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                '}';
    }
}
