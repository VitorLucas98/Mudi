package br.com.vitorluc.mudi.services.impl;

import br.com.vitorluc.mudi.model.Pedido;
import br.com.vitorluc.mudi.repositories.IPedidoRepository;
import br.com.vitorluc.mudi.services.IPedidoService;
import br.com.vitorluc.mudi.services.dtos.PedidoInsertDTO;
import br.com.vitorluc.mudi.services.dtos.PedidoListagemDTO;
import br.com.vitorluc.mudi.services.mappers.PedidoInsertMapper;
import br.com.vitorluc.mudi.services.mappers.PedidoListagemMapper;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Transactional
@Service
@RequiredArgsConstructor
public class PedidoService implements IPedidoService {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private Locale localeBR = new Locale("pt","BR");
    private NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);

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
    public boolean createExcell(ServletContext context, HttpServletRequest request, HttpServletResponse response) {
        String[] HEADERs = { "Id", "Nome do Produto", "Valor Negociado", "Data de Entrega",
                "URL do Produto", "URL da Imagem", "Descrição", "Status"};
        String filePath = context.getRealPath("/resources/report");
        File file = new File(filePath);
        boolean exists = new File(filePath).exists();
        if (!exists){
            new File(filePath).mkdirs();
        }
        try{
            FileOutputStream outputStream = new FileOutputStream(file+"/"+"pedidos"+".xls");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet workSheet = workbook.createSheet("employees");
            workSheet.setDefaultColumnWidth(30);
            List<Pedido> list = repository.buscarTodos();

            HSSFRow headerRow = workSheet.createRow(0);
            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            Integer i = 1;

            for (Pedido pedido : list) {
                Row row = workSheet.createRow(i++);
                row.createCell(0).setCellValue(pedido.getId());
                row.createCell(1).setCellValue(pedido.getNomeProduto());
                row.createCell(2).setCellValue(dinheiro.format(pedido.getValorNegociado()));
                LocalDate data = pedido.getDataDaEntrega();
                row.createCell(3).setCellValue(data != null ? data.format(formatter) : null);
                row.createCell(4).setCellValue(pedido.getUrlProduto());
                row.createCell(5).setCellValue(pedido.getUrlImagem());
                row.createCell(6).setCellValue(pedido.getDescricao());
                row.createCell(7).setCellValue(pedido.getStatus().toString());
            }

            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
            return true;

        }catch (Exception e){
            return false;
        }
    }
}
