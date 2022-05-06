package br.com.vitorluc.mudi.controllers;


import br.com.vitorluc.mudi.services.dtos.PedidoInsertDTO;
import br.com.vitorluc.mudi.services.dtos.PedidoListagemDTO;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface PedidoApi {

    String formulario(PedidoInsertDTO dto);

    String salvar(PedidoInsertDTO pedido, BindingResult result);

    void pedidoToExcel(HttpServletRequest request, HttpServletResponse response);
}
