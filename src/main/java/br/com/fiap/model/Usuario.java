package br.com.fiap.model;

import java.util.regex.Pattern;

public class Usuario {
    private int id;
    private String nome;
    private String senha;
    private String cpf;
    private String email;

    // Construtor com todos os parâmetros
    public Usuario(int id, String nome, String senha, String cpf, String email) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.cpf = cpf;
        this.email = email;
    }

    // Construtor sem ID (para novos usuários)
    public Usuario(String nome, String senha, String cpf, String email) {
        this.nome = nome;
        this.senha = senha;
        this.cpf = cpf;
        this.email = email;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Validação da Senha (mínimo 8 caracteres, pelo menos 1 letra e 1 número)
    public boolean validarSenha(String senha) {
        return senha != null && senha.equals(this.senha);
    }

    // Validação da Senha (mínimo 8 caracteres, pelo menos 1 letra e 1 número)
    public boolean senhaValida() {
        return senha != null && senha.length() >= 8 &&
                Pattern.compile("[a-zA-Z]").matcher(senha).find() &&
                Pattern.compile("\\d").matcher(senha).find();
    }

    // Validação do CPF (formato simples: 11 dígitos numéricos)
    public boolean validarCpf() {
        return cpf != null && cpf.matches("\\d{11}");
    }

    // Validação do Email (formato básico)
    public boolean validarEmail() {
        return email != null && Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$").matcher(email).find();
    }
}
