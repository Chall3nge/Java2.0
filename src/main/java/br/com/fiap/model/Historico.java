package br.com.fiap.model;

import java.time.LocalDateTime;

public class Historico {
    private int id;
    private Usuario usuario;
    private String acao;
    private LocalDateTime data; // Alterado para LocalDateTime

    // Construtores
    public Historico(int id, Usuario usuario, String acao, LocalDateTime data) {
        this.id = id;
        this.usuario = usuario;
        this.acao = acao;
        this.data = data;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Historico{" +
                "id=" + id +
                ", usuario=" + usuario.getNome() + // Exibindo o nome do usuário
                ", acao='" + acao + '\'' +
                ", data=" + data +
                '}';
    }
}
