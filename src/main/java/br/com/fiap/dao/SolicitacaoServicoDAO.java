package br.com.fiap.dao;
import br.com.fiap.model.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SolicitacaoServicoDAO {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String USUARIO = "usuario";
    private static final String SENHA = "senha";

    private Connection obterConexao() throws Exception {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    public boolean adicionar(SolicitacaoServico solicitacao) {
        try (Connection conexao = obterConexao();
             PreparedStatement stmt = conexao.prepareStatement("INSERT INTO SOLICITACOES (USUARIO_ID, DESCRICAO, STATUS) VALUES (?, ?, ?)")) {

            stmt.setInt(1, solicitacao.getUsuario().getId());
            stmt.setString(2, solicitacao.getDescricao());
            stmt.setString(3, solicitacao.getStatus());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean atualizar(SolicitacaoServico solicitacao) {
        String sql = "UPDATE SOLICITACOES_SERVICO SET DESCRICAO = ?, STATUS = ? WHERE ID = ?";
        try (Connection conexao = obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, solicitacao.getDescricao());
            stmt.setString(2, solicitacao.getStatus());
            stmt.setInt(3, solicitacao.getId());

            return stmt.executeUpdate() > 0; // Retorna true se a atualização foi bem-sucedida
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Retorna false em caso de falha
    }
    public boolean remover(int id) {
        String sql = "DELETE FROM SOLICITACOES_SERVICO WHERE ID = ?";
        try (Connection conexao = obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0; // Retorna true se a remoção foi bem-sucedida
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Retorna false em caso de falha
    }

    public SolicitacaoServico buscarPorId(int id) {
        String sql = "SELECT * FROM SOLICITACOES_SERVICO WHERE ID = ?";
        try (Connection conexao = obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Supondo que você tenha um método para buscar o usuário pelo ID
                Usuario usuario = new UsuarioDAO().buscarPorId(rs.getInt("USUARIO_ID"));
                return new SolicitacaoServico(
                        rs.getInt("ID"),
                        usuario,
                        rs.getString("DESCRICAO"),
                        rs.getString("STATUS"),
                        rs.getDate("DATA_SOLICITACAO") // Ajuste conforme necessário
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Retorna null se não encontrar a solicitação
    }

    public List<SolicitacaoServico> listarTodas() {
        String sql = "SELECT * FROM SOLICITACOES_SERVICO";
        try (Connection conexao = obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            List<SolicitacaoServico> solicitacoes = new ArrayList<>();
            while (rs.next()) {
                Usuario usuario = new UsuarioDAO().buscarPorId(rs.getInt("USUARIO_ID"));
                SolicitacaoServico solicitacao = new SolicitacaoServico(
                        rs.getInt("ID"),
                        usuario,
                        rs.getString("DESCRICAO"),
                        rs.getString("STATUS"),
                        rs.getDate("DATA_SOLICITACAO")
                );
                solicitacoes.add(solicitacao);
            }
            return solicitacoes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}