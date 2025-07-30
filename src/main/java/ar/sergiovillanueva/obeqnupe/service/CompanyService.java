package ar.sergiovillanueva.obeqnupe.service;

import ar.sergiovillanueva.obeqnupe.dto.*;
import org.springframework.data.domain.Pageable;

public interface CompanyService {
    FilterDataResponse getFilterData();

    PageDto<CompanyResponse> findAll(FilterRequest filter, Pageable pageable);

    CompanyDetailResponse findOne(long id);
}
