package br.com.vitorluc.mudi.repositories.rowmappers;

import br.com.vitorluc.mudi.model.Pedido;
import br.com.vitorluc.mudi.model.enums.StatusPedido;
import br.com.vitorluc.mudi.util.LocalDateConverter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PedidoRowMapper implements RowMapper<Pedido> {
    @Override
    public Pedido mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pedido pedido = new Pedido();
        pedido.setId(rs.getLong("id"));
        pedido.setNomeProduto(rs.getString("nome_produto"));
        pedido.setValorNegociado(rs.getDouble("valor_negociado"));
        pedido.setDataDaEntrega(LocalDateConverter.convertToEntityAttribute(rs.getDate("data_da_Entrega")));
        pedido.setUrlProduto(rs.getString("url_produto"));
        pedido.setUrlImagem(rs.getString("url_imagem"));
        pedido.setDescricao(rs.getString("descricao"));
        pedido.setStatus(StatusPedido.valueOf(rs.getString("status")));
        return pedido;
    }
}
