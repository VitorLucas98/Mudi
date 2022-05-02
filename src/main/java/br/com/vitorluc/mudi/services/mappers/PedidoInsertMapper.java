package br.com.vitorluc.mudi.services.mappers;

import br.com.vitorluc.mudi.model.Pedido;
import br.com.vitorluc.mudi.services.dtos.PedidoInsertDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoInsertMapper  extends EntityMapper<PedidoInsertDTO, Pedido> {
}
