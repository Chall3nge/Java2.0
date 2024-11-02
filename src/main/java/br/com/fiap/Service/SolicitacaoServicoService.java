package br.com.fiap.Service;
import br.com.fiap.dao.*;
import br.com.fiap.model.*;

import java.util.List;

public class SolicitacaoServicoService {
    private SolicitacaoServicoDAO solicitacaoDAO = new SolicitacaoServicoDAO();

    // Adicionar uma nova solicitação
    public boolean adicionar(SolicitacaoServico solicitacao) {
        if (solicitacao == null || solicitacao.getUsuario() == null || solicitacao.getDescricao() == null) {
            throw new IllegalArgumentException("Dados da solicitação estão incompletos.");
        }
        return solicitacaoDAO.adicionar(solicitacao);
    }
    public List<SolicitacaoServico> listarTodas() {
        SolicitacaoServicoDAO dao = new SolicitacaoServicoDAO();
        return dao.listarTodas();
    }
    // Atualizar uma solicitação existente
    public boolean atualizar(SolicitacaoServico solicitacao) {
        if (solicitacao == null || solicitacao.getId() <= 0) {
            throw new IllegalArgumentException("Solicitação inválida para atualização.");
        }
        return solicitacaoDAO.atualizar(solicitacao);
    }

    // Remover uma solicitação pelo ID
    public boolean remover(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido para remoção.");
        }
        return solicitacaoDAO.remover(id);
    }

    // Buscar uma solicitação pelo ID
    public SolicitacaoServico buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido para busca.");
        }
        return solicitacaoDAO.buscarPorId(id);
    }
}
