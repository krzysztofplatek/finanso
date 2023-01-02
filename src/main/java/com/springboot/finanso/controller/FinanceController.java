package com.springboot.finanso.controller;

import com.springboot.finanso.entity.Finance;
import com.springboot.finanso.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Finance finance = new Finance();
        model.addAttribute("finances", finance);
        return "finances/finance-add-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("financeId") int id, Model model) {
        Finance finance = financeService.findById(id);
        model.addAttribute("finances", finance);
        return "finances/finance-add-form";
    }

    @PostMapping("/save")
    public String saveFinance(@ModelAttribute("finance") Finance finance) {
        financeService.save(finance);
        return "redirect:/finances/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("financeId") int id) {
        financeService.deleteById(id);
        return "redirect:/finances/list";
    }
}
