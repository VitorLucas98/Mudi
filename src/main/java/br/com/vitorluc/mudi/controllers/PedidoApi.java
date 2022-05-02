package br.com.vitorluc.mudi.controllers;


import br.com.vitorluc.mudi.services.dtos.PedidoInsertDTO;
import br.com.vitorluc.mudi.services.dtos.PedidoListagemDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PedidoApi {

    ResponseEntity<List<PedidoListagemDTO>> buscaTodos();

    ResponseEntity<List<PedidoListagemDTO>> buscaPorStatus(String status);

    ResponseEntity<Void> salvar(PedidoInsertDTO pedido);
}
