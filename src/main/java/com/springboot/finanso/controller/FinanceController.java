package com.springboot.finanso.controller;

import com.springboot.finanso.entity.Finance;
import com.springboot.finanso.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

        return findPaginated(1, model);
     /*   List<Finance> finances = financeService.findAll();
        model.addAttribute("finances", finances);
        return "finances/list-finances";*/
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

    @GetMapping("page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 5;
        Page<Finance> page = financeService.findPaginated(pageNo, pageSize);
        List<Finance> finances = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("finances", finances);
        return "finances/list-finances";

    }

}
