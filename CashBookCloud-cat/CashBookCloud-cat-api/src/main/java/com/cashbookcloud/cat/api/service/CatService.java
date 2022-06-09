package com.cashbookcloud.cat.api.service;

import com.cashbookcloud.cat.api.dto.CatDto;
import io.swagger.models.auth.In;

import java.util.List;

public interface CatService {
    CatDto findCatById(Integer id);

    List<CatDto> getAllCat();

    /**
     * 获取所有属于支出的分类
     * @return
     */
    List<CatDto> getAllCatPut();

    /**
     * 获取所有属于收入的分类
     * @return
     */
    List<CatDto> getAllCatInput();
}
