package br.com.fiap.dao;
import br.com.fiap.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReparoDAO {

    private static final Logger logger = Logger.getLogger(ReparoDAO.class.getName());

    // Adicionar um novo reparo
    public boolean adicionar(Reparo reparo) {
        String sql = "INSERT INTO REPAROS (CARRO_ID, PROBLEMA, STATUS) VALUES (?, ?, ?)";
        try (Connection conexao = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, reparo.getCarro().getId());
            stmt.setString(2, reparo.getProblema());
            stmt.setString(3, reparo.getStatus());

            boolean sucesso = stmt.executeUpdate() > 0;
            if (sucesso) {
                logger.info("Reparo adicionado com sucesso: " + reparo);
            }
            return sucesso;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao adicionar reparo: " + reparo, e);
        }
        return false;
    }

    // Atualizar informações de um reparo existente
    public boolean atualizar(Reparo reparo) {
        String sql = "UPDATE REPAROS SET PROBLEMA = ?, STATUS = ? WHERE ID = ?";
        try (Connection conexao = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, reparo.getProblema());
            stmt.setString(2, reparo.getStatus());
            stmt.setInt(3, reparo.getId());

            boolean sucesso = stmt.executeUpdate() > 0;
            if (sucesso) {
                logger.info("Reparo atualizado com sucesso: " + reparo);
            }
            return sucesso;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao atualizar reparo: " + reparo, e);
        }
        return false;
    }

    // Remover um reparo pelo ID
    public boolean remover(int id) {
        String sql = "DELETE FROM REPAROS WHERE ID = ?";
        try (Connection conexao = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            boolean sucesso = stmt.executeUpdate() > 0;
            if (sucesso) {
                logger.info("Reparo removido com sucesso. ID: " + id);
            }
            return sucesso;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao remover reparo com ID: " + id, e);
        }
        return false;
    }

    // Buscar um reparo pelo ID
    public Reparo buscarPorId(int id) {
        String sql = "SELECT * FROM REPAROS WHERE ID = ?";
        Reparo reparo = null;

        try (Connection conexao = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    reparo = new Reparo(
                            rs.getInt("ID"),
                            rs.getInt("CARRO_ID"),  // Ajustar conforme a classe Carro
                            rs.getString("PROBLEMA"),
                            rs.getString("STATUS")
                    );
                    logger.info("Reparo encontrado: " + reparo);
                } else {
                    logger.warning("Nenhum reparo encontrado para o ID: " + id);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao buscar reparo pelo ID: " + id, e);
        }

        return reparo;
    }
}