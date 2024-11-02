package br.com.fiap.Service;

import br.com.fiap.dao.ConexaoBanco;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.model.*;
import static br.com.fiap.dao.CarroDAO.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioService {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    // Declaração do logger específico desta classe
    private static final Logger logger = Logger.getLogger(UsuarioService.class.getName());

    // Adicionar um novo usuário, com validação dos campos
    public boolean adicionar(Usuario usuario) {
        if (usuario == null || !usuario.validarEmail() || !usuario.validarCpf() || !usuario.validarSenha(usuario.getSenha())) {
            throw new IllegalArgumentException("Dados de usuário inválidos.");
        }
        return usuarioDAO.adicionarUsuario(usuario);
    }

    // Atualizar informações do usuário
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
            throw new RuntimeException("Erro ao atualizar usuário", e);
        }
    }

    // Deletar um usuário por ID (falta implementação)
    public void deletar(Long id) {
        // Implementação futura
    }

    // Autenticação do usuário
    public boolean autenticar(String email, String senha) {
        if (email == null || senha == null) {
            throw new IllegalArgumentException("Email e senha não podem ser nulos.");
        }

        Usuario usuario = usuarioDAO.buscarPorEmail(email);

        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        if (!usuario.validarSenha(senha)) {
            throw new IllegalArgumentException("Senha inválida.");
        }

        return true;
    }

    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT ID, NOME, CPF, EMAIL, SENHA FROM USUARIOS";

        try (Connection conexao = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("ID");
                String nome = rs.getString("NOME");
                String cpf = rs.getString("CPF");
                String email = rs.getString("EMAIL");
                String senha = rs.getString("SENHA");

                Usuario usuario = new Usuario(id, nome, cpf, email, senha);
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao listar usuários", e);
            throw new RuntimeException("Erro ao listar usuários", e);
        }
        return usuarios;
    }
    // Buscar usuário por email, com verificação de entrada
    public Usuario buscarPorEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser vazio.");
        }
        return usuarioDAO.buscarPorEmail(email);
    }
}
