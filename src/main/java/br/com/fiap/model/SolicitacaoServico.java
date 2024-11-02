package br.com.fiap.model;

import br.com.fiap.dao.SolicitacaoServicoDAO;

import java.sql.Date;
import java.util.List;

public class SolicitacaoServico {
    private int id;          // ID da solicitação
    private Usuario usuario;  // Objeto do tipo Usuario
    private String descricao; // Descrição do serviço solicitado
    private String status;    // Status da solicitação

    // Construtor
    public SolicitacaoServico(Usuario usuario, String descricao, String status) {
        this.usuario = usuario;
        this.descricao = descricao;
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }
    public SolicitacaoServico(int id, Usuario usuario, String descricao, String status, Date dataSolicitacao) {

    }

    // Getters e Setters
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return 0;
    }
}