package ar.sergiovillanueva.obeqnupe.controller;

import ar.sergiovillanueva.obeqnupe.dto.FilterDataResponse;
import ar.sergiovillanueva.obeqnupe.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {
    private final CompanyService companyService;

    public HomeController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public String index(Model model) {
        FilterDataResponse filterData = companyService.getFilterData();

        model.addAttribute("filterData", filterData);

        return "index";
    }

}
