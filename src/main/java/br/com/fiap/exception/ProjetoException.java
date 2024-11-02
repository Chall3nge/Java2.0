package br.com.fiap.exception;

public class ProjetoException extends Exception {

    // Construtor com uma mensagem de erro
    public ProjetoException(String message) {
        super(message);
    }

    // Construtor com uma mensagem e a causa (exceção original)
    public ProjetoException(String message, Throwable cause) {
        super(message, cause);
    }
}