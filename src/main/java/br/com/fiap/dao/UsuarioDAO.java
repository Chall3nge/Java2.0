package br.com.fiap.dao;
import br.com.fiap.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO {

    private static final Logger logger = Logger.getLogger(UsuarioDAO.class.getName());

    // Buscar um usuário por email
    public Usuario buscarPorEmail(String email) {
        String sql = "SELECT * FROM USUARIOS WHERE EMAIL = ?";
        Usuario usuario = null;

        try (Connection conexao = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario(rs.getString("NOME"), rs.getString("SENHA"), rs.getString("CPF"), rs.getString("EMAIL"));
                    logger.info("Usuário encontrado por email: " + email);
                } else {
                    logger.warning("Nenhum usuário encontrado com o email: " + email);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao buscar usuário por email: " + email, e);
        }

        return usuario;
    }

    // Adicionar um novo usuário
    public boolean adicionarUsuario(Usuario usuario) {
        String sql = "INSERT INTO USUARIOS (NOME, SENHA, CPF, EMAIL) VALUES (?, ?, ?, ?)";
        try (Connection conexao = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getCpf());
            stmt.setString(4, usuario.getEmail());

            boolean sucesso = stmt.executeUpdate() > 0;
            if (sucesso) {
                logger.info("Usuário adicionado com sucesso: " + usuario);
            }
            return sucesso;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao adicionar usuário: " + usuario, e);
        }
        return false;
    }

    // Buscar um usuário por ID
    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM USUARIOS WHERE ID = ?";
        Usuario usuario = null;

        try (Connection conexao = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario(rs.getInt("ID"), rs.getString("NOME"), rs.getString("SENHA"), rs.getString("CPF"), rs.getString("EMAIL"));
                    logger.info("Usuário encontrado por ID: " + id);
                } else {
                    logger.warning("Nenhum usuário encontrado com o ID: " + id);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao buscar usuário por ID: " + id, e);
        }

        return usuario;
    }

    // Remover um usuário pelo ID
    public boolean remover(int id) {
        String sql = "DELETE FROM USUARIOS WHERE ID = ?";
        try (Connection conexao = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            boolean sucesso = stmt.executeUpdate() > 0;
            if (sucesso) {
                logger.info("Usuário removido com sucesso. ID: " + id);
            }
            return sucesso;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao remover usuário com ID: " + id, e);
        }
        return false;
    }

    // Atualizar um usuário
    public boolean atualizar(Usuario usuario) {
        String sql = "UPDATE USUARIOS SET NOME = ?, SENHA = ?, CPF = ?, EMAIL = ? WHERE ID = ?";
        try (Connection conexao = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getCpf());
            stmt.setString(4, usuario.getEmail());
            stmt.setInt(5, usuario.getId());

            boolean sucesso = stmt.executeUpdate() > 0;
            if (sucesso) {
                logger.info("Usuário atualizado com sucesso: " + usuario);
            }
            return sucesso;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao atualizar usuário: " + usuario, e);
        }
        return false;
    }
}