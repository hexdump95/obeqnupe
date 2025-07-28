package ar.sergiovillanueva.obeqnupe.controller;

import ar.sergiovillanueva.obeqnupe.dto.CompanyDetailResponse;
import ar.sergiovillanueva.obeqnupe.dto.CompanyResponse;
import ar.sergiovillanueva.obeqnupe.dto.FilterRequest;
import ar.sergiovillanueva.obeqnupe.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<CompanyResponse> getAll(FilterRequest filter) {
        return companyService.findAll(filter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDetailResponse> getOne(@PathVariable long id) {
        CompanyDetailResponse company = companyService.findOne(id);
        return ResponseEntity.ok(company);
    }

}
