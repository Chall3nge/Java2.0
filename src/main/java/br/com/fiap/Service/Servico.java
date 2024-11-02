package br.com.fiap.Service;

import java.util.List;

public interface Servico<T> {
    void adicionar(T objeto);
    void atualizar(T objeto);
    List<T> listarTodas();
    void remover(int id);
    T buscarPorId(int id);
}