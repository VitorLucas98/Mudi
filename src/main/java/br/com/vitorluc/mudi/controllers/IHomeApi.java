package br.com.vitorluc.mudi.controllers;

import org.springframework.ui.Model;

public interface IHomeApi {

    String home(Model model);

    String porStatus(String status, Model model);

    String onError();
}
