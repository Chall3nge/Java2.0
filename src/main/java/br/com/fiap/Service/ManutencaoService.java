package br.com.fiap.Service;

import br.com.fiap.dao.*;
import br.com.fiap.model.*;

import java.util.List;

public class ManutencaoService {

    private ManutencaoDAO manutencaoDAO = new ManutencaoDAO();

    // Adicionar uma nova manutenção
    public void adicionar(Manutencao manutencao) {
        if (manutencao != null) {
            manutencaoDAO.adicionarManutencao(manutencao);
            System.out.println("Manutenção adicionada com sucesso.");
        } else {
            System.out.println("Falha ao adicionar a manutenção. Dados inválidos.");
        }
    }
    public List<Manutencao> listarTodas() {
        return manutencaoDAO.listarTodas();
    }

    // Atualizar uma manutenção existente
    public void atualizar(Manutencao manutencao) {
        if (manutencao != null && manutencao.getId() > 0) {
            manutencaoDAO.atualizarManutencao(manutencao);
            System.out.println("Manutenção atualizada com sucesso.");
        } else {
            System.out.println("Falha ao atualizar a manutenção. Verifique os dados.");
        }
    }


    // Remover uma manutenção pelo ID
    public void remover(int id) {
        if (id > 0) {
            manutencaoDAO.removerManutencao(id);
            System.out.println("Manutenção removida com sucesso.");
        } else {
            System.out.println("ID inválido para remoção.");
        }
    }

    // Buscar uma manutenção pelo ID
    public Manutencao buscarPorId(int id) {
        if (id > 0) {
            Manutencao manutencao = manutencaoDAO.buscarPorId(id);
            if (manutencao != null) {
                System.out.println("Manutenção encontrada: " + manutencao.getDescricao());
            } else {
                System.out.println("Manutenção não encontrada.");
            }
            return manutencao;
        } else {
            System.out.println("ID inválido para busca.");
            return null;
        }
    }
}