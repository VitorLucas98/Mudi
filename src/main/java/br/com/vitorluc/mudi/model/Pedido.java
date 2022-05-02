package br.com.vitorluc.mudi.model;

import br.com.vitorluc.mudi.model.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Pedido {

    @Id
    private Long id;
    private String nomeProduto;
    private Double valorNegociado;
    private LocalDate dataDaEntrega;
    private String urlProduto;
    private String urlImagem;
    private String descricao;
    private StatusPedido status = StatusPedido.AGUARDANDO;
}
