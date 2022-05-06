package br.com.vitorluc.mudi.services;

import br.com.vitorluc.mudi.model.Pedido;
import br.com.vitorluc.mudi.services.dtos.PedidoInsertDTO;
import br.com.vitorluc.mudi.services.dtos.PedidoListagemDTO;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.util.List;

public interface IPedidoService {

    List<PedidoListagemDTO> buscarTodos();
    List<PedidoListagemDTO> buscarPorStatus(String status);
    int salvar(PedidoInsertDTO pedido);
    boolean createExcell(ServletContext context, HttpServletRequest request, HttpServletResponse response);

}
