package br.com.fiap.dao;
import br.com.fiap.model.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarroDAO {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String USUARIO = "rm558798";
    private static final String SENHA = "Fiap24";

    private static final Logger logger = Logger.getLogger(CarroDAO.class.getName());

    private Connection obterConexao() throws SQLException {
        logger.info("Obtendo conexão com o banco de dados.");
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    public List<Carro> listarTodos() {
        String sql = "SELECT * FROM CARROS";
        List<Carro> carros = new ArrayList<>();
        try (Connection conexao = obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Carro carro = new Carro(rs.getString("PLACA"), rs.getString("MODELO"), rs.getString("MARCA"));
                carros.add(carro);
            }
            logger.info("Lista de carros obtida com sucesso.");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao listar carros.", e);
        }
        return carros;
    }

    public boolean adicionarCarro(Carro carro) {
        String sql = "INSERT INTO CARROS (PLACA, MODELO, MARCA) VALUES (?, ?, ?)";
        try (Connection conexao = obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, carro.getPlaca());
            stmt.setString(2, carro.getModelo());
            stmt.setString(3, carro.getMarca());
            boolean sucesso = stmt.executeUpdate() > 0;
            logger.info("Carro adicionado com sucesso: " + carro);
            return sucesso;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao adicionar carro: " + carro, e);
        }
        return false;
    }

    public boolean atualizarCarro(Carro carro) {
        String sql = "UPDATE CARROS SET MODELO = ?, MARCA = ? WHERE PLACA = ?";
        try (Connection conexao = obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, carro.getModelo());
            stmt.setString(2, carro.getMarca());
            stmt.setString(3, carro.getPlaca());
            boolean sucesso = stmt.executeUpdate() > 0;
            logger.info("Carro atualizado com sucesso: " + carro);
            return sucesso;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao atualizar carro: " + carro, e);
        }
        return false;
    }

    public boolean removerCarro(String placa) {
        String sql = "DELETE FROM CARROS WHERE PLACA = ?";
        try (Connection conexao = obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, placa);
            boolean sucesso = stmt.executeUpdate() > 0;
            logger.info("Carro removido com sucesso. Placa: " + placa);
            return sucesso;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao remover carro. Placa: " + placa, e);
        }
        return false;
    }

    public Carro buscarPorPlaca(String placa) {
        String sql = "SELECT * FROM CARROS WHERE PLACA = ?";
        try (Connection conexao = obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, placa);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Carro carro = new Carro(rs.getString("PLACA"), rs.getString("MODELO"), rs.getString("MARCA"));
                logger.info("Carro encontrado: " + carro);
                return carro;
            } else {
                logger.warning("Nenhum carro encontrado para a placa: " + placa);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao buscar carro pela placa: " + placa, e);
        }
        return null;
    }
}