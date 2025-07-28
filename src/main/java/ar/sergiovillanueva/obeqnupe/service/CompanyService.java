package ar.sergiovillanueva.obeqnupe.service;

import ar.sergiovillanueva.obeqnupe.dto.CompanyDetailResponse;
import ar.sergiovillanueva.obeqnupe.dto.CompanyResponse;
import ar.sergiovillanueva.obeqnupe.dto.FilterRequest;
import ar.sergiovillanueva.obeqnupe.dto.FiltersDto;
import ar.sergiovillanueva.obeqnupe.entity.Company;

import java.util.List;

public interface CompanyService {
    FiltersDto getFilters();

    List<Company> findAll();

    List<CompanyResponse> findAll(FilterRequest filter);

    CompanyDetailResponse findOne(long id);
}
