package br.com.vitorluc.mudi.services.dtos;

import br.com.vitorluc.mudi.model.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoListagemDTO {

    private Long id;
    private String nomeProduto;
    private Double valorNegociado;
    private LocalDate dataDaEntrega;
    private String urlProduto;
    private String urlImagem;
    private String descricao;
    private StatusPedido status;
}
