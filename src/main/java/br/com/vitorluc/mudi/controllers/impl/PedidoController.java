package br.com.vitorluc.mudi.controllers.impl;

import br.com.vitorluc.mudi.controllers.PedidoApi;
import br.com.vitorluc.mudi.model.Pedido;
import br.com.vitorluc.mudi.services.IPedidoService;
import br.com.vitorluc.mudi.services.dtos.PedidoInsertDTO;
import br.com.vitorluc.mudi.services.dtos.PedidoListagemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController implements PedidoApi {

    @Autowired
    private IPedidoService service;

    @Override
    @GetMapping
    public ResponseEntity<List<PedidoListagemDTO>> buscaTodos() {
        return ResponseEntity.ok(service.buscarTodos());
    }

    @Override
    @GetMapping("/{status}")
    public ResponseEntity<List<PedidoListagemDTO>> buscaPorStatus(@PathVariable String status) {
        return ResponseEntity.ok(service.buscarPorStatus(status));
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> salvar(@Valid @RequestBody PedidoInsertDTO pedido) {
        service.salvar(pedido);
        return ResponseEntity.noContent().build();
    }
}
