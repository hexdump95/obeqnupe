package ar.sergiovillanueva.obeqnupe.service;

import ar.sergiovillanueva.obeqnupe.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CompanyService {
    FilterDataResponse getFilterData();

    PageDto<CompanyResponse> findAll(FilterRequest filter, Pageable pageable);

    CompanyDetailResponse findOne(UUID id);
}
