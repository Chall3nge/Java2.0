package br.com.fiap.dao;

import br.com.fiap.model.Manutencao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManutencaoDAO {

    private static final Logger logger = Logger.getLogger(ManutencaoDAO.class.getName());
    public class TestaConexao {
        private static final String URL = "jdbc:oracle:thin:@localhost:1521:ORCL";
        private static final String USER = "rm558798";
        private static final String PASSWORD = "Fiap24";

        public static void main(String[] args) {
            try {
                Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Falha ao conectar ao banco de dados.");
            }
        }
    }
    // Adicionar uma nova manutenção
    public boolean adicionarManutencao(Manutencao manutencao) {
        String sql = "INSERT INTO MANUTENCOES (descricao, data, carroId) VALUES (?, ?, ?)";
        try (Connection conexao = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, manutencao.getDescricao());
            stmt.setDate(2, manutencao.getData());
            stmt.setInt(3, manutencao.getCarroId());

            int rowsAffected = stmt.executeUpdate();
            logger.info("Manutenção adicionada com sucesso: " + manutencao);
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao adicionar manutenção: " + manutencao, e);
            return false;
        }
    }

    // Atualizar uma manutenção existente
    public boolean atualizarManutencao(Manutencao manutencao) {
        String sql = "UPDATE MANUTENCOES SET descricao = ?, data = ?, carroId = ? WHERE id = ?";
        try (Connection conexao = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, manutencao.getDescricao());
            stmt.setDate(2, manutencao.getData());
            stmt.setInt(3, manutencao.getCarroId());
            stmt.setInt(4, manutencao.getId());

            int rowsAffected = stmt.executeUpdate();
            logger.info("Manutenção atualizada com sucesso: " + manutencao);
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao atualizar manutenção: " + manutencao, e);
            return false;
        }
    }

    // Remover uma manutenção pelo ID
    public boolean removerManutencao(int id) {
        String sql = "DELETE FROM MANUTENCOES WHERE id = ?";
        try (Connection conexao = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            logger.info("Manutenção removida com sucesso. ID: " + id);
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao remover manutenção. ID: " + id, e);
            return false;
        }
    }

    // Listar todas as manutenções
    public List<Manutencao> listarTodas() {
        List<Manutencao> manutencoes = new ArrayList<>();
        String sql = "SELECT * FROM MANUTENCOES";
        try (Connection conexao = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Manutencao manutencao = new Manutencao(
                        rs.getInt("id"),
                        rs.getString("descricao"),
                        rs.getDate("data"),
                        rs.getInt("carroId")
                );
                manutencoes.add(manutencao);
            }
            return manutencoes;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao listar manutenções", e);
            return new ArrayList<>();
        }
    }

    // Buscar uma manutenção pelo ID
    public Manutencao buscarPorId(int id) {
        String sql = "SELECT * FROM MANUTENCOES WHERE id = ?";
        try (Connection conexao = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Manutencao(
                            rs.getInt("id"),
                            rs.getString("descricao"),
                            rs.getDate("data"),
                            rs.getInt("carroId")
                    );
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao buscar manutenção pelo ID: " + id, e);
        }
        return null;
    }
}
