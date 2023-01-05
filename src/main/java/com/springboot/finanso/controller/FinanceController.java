package com.springboot.finanso.controller;

import com.springboot.finanso.entity.Finance;
import com.springboot.finanso.service.FinanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
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

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/list")
    public String listFinances(Model model) {
        return findPaginated(1, "id", "asc", model);
    }

    @GetMapping("/search")
    public String searchFinanses(@Param(value = "keyword") String keyword, Model model) {
        model.addAttribute("finances", financeService.findByKeyword(keyword));
        model.addAttribute("financesSize", financeService.findByKeyword(keyword).size());
        return "finances/list-search-results";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Finance finance = new Finance();
        model.addAttribute("finance", finance);
        return "finances/finance-add-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("financeId") int id, Model model) {
        Finance finance = financeService.findById(id);
        model.addAttribute("finance", finance);
        return "finances/finance-add-form";
    }

    @PostMapping("/save")
    public String saveFinance(@Valid @ModelAttribute("finance") Finance finance,
                              BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "finances/finance-add-form";

        financeService.save(finance);
        return "redirect:/finances/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("financeId") int id) {
        financeService.deleteById(id);
        return "redirect:/finances/list";
    }

    @GetMapping("page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam(name = "sortField") String sortField,
                                @RequestParam(name = "sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;
        Page<Finance> page = financeService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Finance> finances = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("finances", finances);

        return "finances/list-finances";
    }

}
