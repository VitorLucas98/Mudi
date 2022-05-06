package br.com.vitorluc.mudi.controllers.impl;

import br.com.vitorluc.mudi.controllers.PedidoApi;
import br.com.vitorluc.mudi.services.IPedidoService;
import br.com.vitorluc.mudi.services.dtos.PedidoInsertDTO;
import br.com.vitorluc.mudi.util.FileHandelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/pedido")
public class PedidoController implements PedidoApi {

    @Autowired
    private IPedidoService service;

    @Autowired
    private ServletContext context;

    @Autowired
    private FileHandelService fileHandelService;

    @Override
    @GetMapping("/formulario")
    public String formulario(PedidoInsertDTO pedido) {
        return "pedido/formulario";
    }

    @Override
    @PostMapping("/new")
    public String salvar(@Valid PedidoInsertDTO pedido, BindingResult result) {
        if(result.hasErrors()) {
            return "pedido/formulario";
        }
        service.salvar(pedido);
        return "redirect:/";
    }

    @Override
    public void pedidoToExcel(HttpServletRequest request, HttpServletResponse response) {
        if (service.createExcell(context, request, response)){
            String fullPath = request.getServletContext().getRealPath("/resources/report/" + "employees" + ".xls");
            fileHandelService.filedownload(fullPath, response,"pedidos.xls");
        }
    }

}
