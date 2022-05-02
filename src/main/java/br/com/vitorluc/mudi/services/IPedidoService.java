package br.com.vitorluc.mudi.services;

import br.com.vitorluc.mudi.model.Pedido;
import br.com.vitorluc.mudi.services.dtos.PedidoInsertDTO;
import br.com.vitorluc.mudi.services.dtos.PedidoListagemDTO;

import java.util.List;

public interface IPedidoService {

    List<PedidoListagemDTO> buscarTodos();
    List<PedidoListagemDTO> buscarPorStatus(String status);
    int salvar(PedidoInsertDTO pedido);

}
