package br.com.vitorluc.mudi.controllers.impl;

import br.com.vitorluc.mudi.controllers.IHomeApi;
import br.com.vitorluc.mudi.services.IPedidoService;
import br.com.vitorluc.mudi.services.dtos.PedidoListagemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController implements IHomeApi {

    @Autowired
    private IPedidoService service;

    @Override
    @GetMapping
    public String home(Model model) {
        List<PedidoListagemDTO> pedidos = service.buscarTodos();
        model.addAttribute("pedidos", pedidos);
        return "home";
    }

    @Override
    @GetMapping("status/{status}")
    public String porStatus(@PathVariable String status, Model model) {
        List<PedidoListagemDTO> pedidos = service.buscarPorStatus(status);
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("status", status);
        return "home";
    }

    @Override
    @ExceptionHandler(IllegalArgumentException.class)
    public String onError() {
        return "redirect:/home";
    }

}
