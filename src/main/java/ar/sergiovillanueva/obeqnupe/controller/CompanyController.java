package ar.sergiovillanueva.obeqnupe.controller;

import ar.sergiovillanueva.obeqnupe.dto.CompanyDetailResponse;
import ar.sergiovillanueva.obeqnupe.dto.CompanyResponse;
import ar.sergiovillanueva.obeqnupe.dto.FilterRequest;
import ar.sergiovillanueva.obeqnupe.dto.PageDto;
import ar.sergiovillanueva.obeqnupe.service.CompanyService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {
    private final CompanyService companyService;
    private static final Integer PAGE_SIZE = 12;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public PageDto<CompanyResponse> getAll(FilterRequest filter,
                                           @RequestParam(defaultValue = "1") int page
    ) {
        try {
            return companyService.findAll(filter, PageRequest.of(page - 1, PAGE_SIZE));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDetailResponse> getOne(@PathVariable long id) {
        CompanyDetailResponse company = companyService.findOne(id);
        return ResponseEntity.ok(company);
    }

}
