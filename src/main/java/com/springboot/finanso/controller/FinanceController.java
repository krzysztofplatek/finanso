package com.springboot.finanso.controller;

import com.springboot.finanso.entity.Finance;
import com.springboot.finanso.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/finances")
public class FinanceController {

    private final FinanceService financeService;

    @Autowired
    public FinanceController(FinanceService financeService) {
        this.financeService = financeService;
    }

    @GetMapping("/list")
    public String listFinances(Model model) {
        List<Finance> finances = financeService.findAll();
        model.addAttribute("finances", finances);
        return "finances/list-finances";
    }
}
