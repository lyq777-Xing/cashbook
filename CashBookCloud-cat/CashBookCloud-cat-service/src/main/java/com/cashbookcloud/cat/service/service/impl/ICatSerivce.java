package com.cashbookcloud.cat.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cashbookcloud.cat.api.dto.CatDto;
import com.cashbookcloud.cat.api.service.CatService;
import com.cashbookcloud.cat.service.covert.CatCovert;
import com.cashbookcloud.cat.service.entity.Cat;
import com.cashbookcloud.cat.service.mapper.CatMapper;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<CatDto> getAllCat() {
        List<Cat> cats = catMapper.selectList(null);
        ArrayList<CatDto> catDtos = new ArrayList<>();
        for (Cat c:cats) {
            catDtos.add(CatCovert.INSTANCE.entity2dto(c));
        }
        return catDtos;
    }

    @Override
    public List<CatDto> getAllCatPut() {
        QueryWrapper<Cat> wrapper = new QueryWrapper<>();
        wrapper.eq("cat_desc","支出");
        List<Cat> cats = catMapper.selectList(wrapper);
        ArrayList<CatDto> catDtos = new ArrayList<>();
        for (Cat c:cats) {
            catDtos.add(CatCovert.INSTANCE.entity2dto(c));
        }
        return catDtos;
    }

    @Override
    public List<CatDto> getAllCatInput() {
        QueryWrapper<Cat> wrapper = new QueryWrapper<>();
        wrapper.eq("cat_desc","收入");
        List<Cat> cats = catMapper.selectList(wrapper);
        ArrayList<CatDto> catDtos = new ArrayList<>();
        for (Cat c:cats) {
            catDtos.add(CatCovert.INSTANCE.entity2dto(c));
        }
        return catDtos;
    }

    @Override
    public void add(CatDto catDto) {
        Cat cat = CatCovert.INSTANCE.dto2entity(catDto);
        catMapper.insert(cat);
    }

    @Override
    public CatDto findByCatName(String catName) {
        QueryWrapper<Cat> wrapper = new QueryWrapper<>();
        wrapper.eq("cat_name",catName);
        Cat cat = catMapper.selectOne(wrapper);
        CatDto catDto = CatCovert.INSTANCE.entity2dto(cat);
        return catDto;
    }

    @Override
    public void upd(CatDto catDto) {
        Cat cat = CatCovert.INSTANCE.dto2entity(catDto);
        catMapper.updateById(cat);
    }

    @Override
    public void del(Integer id) {
        catMapper.deleteById(id);
    }
}
