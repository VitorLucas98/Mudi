package br.com.vitorluc.mudi.repositories;

import br.com.vitorluc.mudi.model.Pedido;

import java.util.List;

public interface IPedidoRepository {
    List<Pedido> buscarTodos();
    List<Pedido> buscarPorStatus(String status);
    int salvar(Pedido pedido);
}
