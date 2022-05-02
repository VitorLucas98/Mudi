package br.com.vitorluc.mudi.services.impl;

import br.com.vitorluc.mudi.model.Pedido;
import br.com.vitorluc.mudi.repositories.IPedidoRepository;
import br.com.vitorluc.mudi.services.IPedidoService;
import br.com.vitorluc.mudi.services.dtos.PedidoInsertDTO;
import br.com.vitorluc.mudi.services.dtos.PedidoListagemDTO;
import br.com.vitorluc.mudi.services.mappers.PedidoInsertMapper;
import br.com.vitorluc.mudi.services.mappers.PedidoListagemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class PedidoService implements IPedidoService {

    @Autowired
    private IPedidoRepository repository;

    private final PedidoListagemMapper pedidoListagemMapper;

    private final PedidoInsertMapper pedidoInsertMapper;

    @Override
    public List<PedidoListagemDTO> buscarTodos() {
        return pedidoListagemMapper.toDto(repository.buscarTodos());
    }

    @Override
    public List<PedidoListagemDTO> buscarPorStatus(String status) {
        return pedidoListagemMapper.toDto(repository.buscarPorStatus(status.toUpperCase()));
    }

    @Override
    public int salvar(PedidoInsertDTO pedido) {
        return repository.salvar(pedidoInsertMapper.toEntity(pedido));
    }
}
