package br.com.vitorluc.mudi.services.impl;

import br.com.vitorluc.mudi.model.Pedido;
import br.com.vitorluc.mudi.repositories.IPedidoRepository;
import br.com.vitorluc.mudi.services.IPedidoService;
import br.com.vitorluc.mudi.services.dtos.PedidoInsertDTO;
import br.com.vitorluc.mudi.services.dtos.PedidoListagemDTO;
import br.com.vitorluc.mudi.services.mappers.PedidoInsertMapper;
import br.com.vitorluc.mudi.services.mappers.PedidoListagemMapper;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class PedidoService implements IPedidoService {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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

    @Override
    public ByteArrayInputStream PedidosToExcel() {
        String[] HEADERs = { "Id", "Nome do Produto", "Valor Negociado", "Data de Entrega",
        "URL do Produto", "URL da Imagem", "Descrição", "Status"};
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("pedido");
            List<Pedido> list = repository.buscarTodos();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            XSSFCellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontHeight(16.0);
            style.setFont(font);

            XSSFRow headerRow = sheet.createRow(0);
            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (Pedido pedido : list) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(pedido.getId());
                row.createCell(1).setCellValue(pedido.getNomeProduto());
                row.createCell(2).setCellValue(pedido.getValorNegociado());
                LocalDate data = pedido.getDataDaEntrega();
                row.createCell(3).setCellValue(data != null ? data.format(formatter) : null);
                row.createCell(4).setCellValue(pedido.getUrlProduto());
                row.createCell(5).setCellValue(pedido.getUrlImagem());
                row.createCell(6).setCellValue(pedido.getDescricao());
                row.createCell(7).setCellValue(pedido.getStatus().toString());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }
}
