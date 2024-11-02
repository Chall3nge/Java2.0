package br.com.fiap.Service;

import br.com.fiap.model.Reparo;
import br.com.fiap.dao.ReparoDAO;

import java.util.ArrayList;
import java.util.List;

public class ReparoService implements Servico<Reparo> {
    private ReparoDAO reparoDAO = new ReparoDAO();

    @Override
    public void adicionar(Reparo reparo) {
        if (validarReparo(reparo)) {
            reparoDAO.adicionar(reparo);
            System.out.println("Reparo adicionado com sucesso.");
        } else {
            System.out.println("Dados do reparo inválidos. Não foi possível adicionar.");
        }
    }
    @Override
    public List<Reparo> listarTodas() {
        // Implementação do método para listar todos os reparos
        return new ArrayList<>(); // Exemplo de retorno, substitua pela lógica real
    }
    @Override
    public void atualizar(Reparo reparo) {
        if (reparo != null && reparo.getId() > 0) {
            reparoDAO.atualizar(reparo);
            System.out.println("Reparo atualizado com sucesso.");
        } else {
            System.out.println("Dados do reparo inválidos para atualização.");
        }
    }

    @Override
    public void remover(int id) {
        if (id > 0) {
            reparoDAO.remover(id);
            System.out.println("Reparo removido com sucesso.");
        } else {
            System.out.println("ID inválido para remoção.");
        }
    }

    @Override
    public Reparo buscarPorId(int id) {
        if (id > 0) {
            return reparoDAO.buscarPorId(id);
        }
        System.out.println("ID inválido para busca.");
        return null;
    }

    private boolean validarReparo(Reparo reparo) {
        return reparo != null && reparo.getProblema() != null && !reparo.getProblema().isEmpty();
    }
}