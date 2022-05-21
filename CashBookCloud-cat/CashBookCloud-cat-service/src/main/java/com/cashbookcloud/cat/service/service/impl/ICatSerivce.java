package com.cashbookcloud.cat.service.service.impl;

import com.cashbookcloud.cat.api.dto.CatDto;
import com.cashbookcloud.cat.api.service.CatService;
import com.cashbookcloud.cat.service.covert.CatCovert;
import com.cashbookcloud.cat.service.entity.Cat;
import com.cashbookcloud.cat.service.mapper.CatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@org.apache.dubbo.config.annotation.Service
public class ICatSerivce implements CatService {

    @Autowired
    private CatMapper catMapper;

    @Override
    public CatDto findCatById(Integer id) {
        Cat cat = catMapper.selectById(id);
        CatDto catDto = CatCovert.INSTANCE.entity2dto(cat);
        return catDto;
    }
}
