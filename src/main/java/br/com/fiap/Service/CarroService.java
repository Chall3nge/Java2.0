package br.com.fiap.Service;

import br.com.fiap.model.*;
import br.com.fiap.dao.*;

import java.util.List;

public class CarroService {

    public List<Carro> listarTodos() {
        CarroDAO carroDAO = new CarroDAO();
        return carroDAO.listarTodos();
    }

    public void cadastrar(Carro carro) {
        if (carro == null || carro.getPlaca() == null || carro.getModelo() == null || carro.getMarca() == null) {
            throw new IllegalArgumentException("Dados do carro estão incompletos.");
        }
        CarroDAO carroDAO = new CarroDAO();
        carroDAO.adicionarCarro(carro);
    }

    public Carro buscarPorPlaca(String placa) {
        if (placa == null || placa.trim().isEmpty()) {
        throw new IllegalArgumentException("ID inválido para busca.");
    }
    CarroDAO carroDAO = new CarroDAO();
    return carroDAO.buscarPorPlaca(placa);
}

   public void atualizar(Carro carro) {
    if (carro == null || carro.getId() <= 0 || carro.getPlaca() == null || carro.getModelo() == null || carro.getMarca() == null) {
        throw new IllegalArgumentException("Dados do carro estão incompletos ou inválidos.");
    }
    CarroDAO carroDAO = new CarroDAO();
    carroDAO.atualizarCarro(carro);
}

   public void removerCarro(String placa) {
       if (placa == null || placa.trim().isEmpty()) {
        throw new IllegalArgumentException("ID inválido para remoção.");
    }
    CarroDAO carroDAO = new CarroDAO();
    carroDAO.removerCarro(placa);
}

}