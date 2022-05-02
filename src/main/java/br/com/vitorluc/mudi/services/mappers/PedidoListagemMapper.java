package br.com.vitorluc.mudi.services.mappers;

import br.com.vitorluc.mudi.model.Pedido;
import br.com.vitorluc.mudi.services.dtos.PedidoListagemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoListagemMapper extends EntityMapper<PedidoListagemDTO, Pedido> {
}
