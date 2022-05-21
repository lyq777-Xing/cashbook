package com.cashbookcloud.cat.api.service;

import com.cashbookcloud.cat.api.dto.CatDto;
import io.swagger.models.auth.In;

public interface CatService {
    CatDto findCatById(Integer id);
}
