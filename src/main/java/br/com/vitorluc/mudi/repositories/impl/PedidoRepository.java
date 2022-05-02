package br.com.vitorluc.mudi.repositories.impl;

import br.com.vitorluc.mudi.model.Pedido;
import br.com.vitorluc.mudi.repositories.IPedidoRepository;
import br.com.vitorluc.mudi.repositories.rowmappers.PedidoRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PedidoRepository implements IPedidoRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Pedido> buscarTodos() {

        String sql = "SELECT p.id, p.nome_produto, p.valor_negociado, p.data_da_entrega," +
                " p.url_produto, p.url_imagem, p.descricao, p.status FROM pedido p";

        return namedParameterJdbcTemplate.query(sql, new PedidoRowMapper());
    }

    @Override
    public List<Pedido> buscarPorStatus(String status) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("P_STATUS", status);

        String sql = "SELECT p.id, p.nome_produto, p.valor_negociado, p.data_da_entrega," +
                " p.url_produto, p.url_imagem, p.descricao, p.status FROM pedido p " +
                "WHERE p.status = :P_STATUS";

        return namedParameterJdbcTemplate.query(sql, sqlParameterSource, new PedidoRowMapper());
    }

    @Override
    public int salvar(Pedido pedido) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("P_NOME_PRODUTO", pedido.getNomeProduto())
                .addValue("P_VALOR_NEGOCIADO", pedido.getValorNegociado())
                .addValue("P_DATA_DA_ENTREGA", pedido.getDataDaEntrega())
                .addValue("P_URL_PRODUTO", pedido.getUrlProduto())
                .addValue("P_URL_IMAGEM", pedido.getUrlImagem())
                .addValue("P_DESCRICAO", pedido.getDescricao())
                .addValue("P_STATUS", pedido.getStatus().toString());


        String sql = "INSERT INTO Pedido (nome_Produto, valor_Negociado, data_da_Entrega, url_Produto, url_Imagem, descricao, status ) " +
                "VALUES (:P_NOME_PRODUTO, :P_VALOR_NEGOCIADO, :P_DATA_DA_ENTREGA, :P_URL_PRODUTO, :P_URL_IMAGEM, :P_DESCRICAO, :P_STATUS)";

        return namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }
}
