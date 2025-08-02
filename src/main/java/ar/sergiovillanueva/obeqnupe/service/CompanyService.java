package ar.sergiovillanueva.obeqnupe.service;

import ar.sergiovillanueva.obeqnupe.dto.*;

import java.util.UUID;

public interface CompanyService {
    FilterDataResponse getFilterData();

    PageDto<CompanyResponse> findAll(FilterRequest filter, int page);

    CompanyDetailResponse findOne(UUID id);
}
