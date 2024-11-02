package br.com.fiap.dao;
import br.com.fiap.model.*;
import br.com.fiap.Service.*;

public class UsuarioService {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    // Método para adicionar um novo usuário
    public boolean adicionar(Usuario usuario) {
        return usuarioDAO.adicionarUsuario(usuario); // Chama o método do DAO
    }

    // Método para fazer login do usuário
    public boolean fazerLogin(String email, String senha) {
        Usuario usuario = usuarioDAO.buscarPorEmail(email); // Chama o método do DAO
        if (usuario != null) {
            return usuario.validarSenha(senha); // Valida a senha
        }
        return false; // Retorna false se o usuário não foi encontrado
    }

    // Método para buscar usuário por email
    public Usuario buscarPorEmail(String email) {
        return usuarioDAO.buscarPorEmail(email); // Chama o método do DAO
    }


}
