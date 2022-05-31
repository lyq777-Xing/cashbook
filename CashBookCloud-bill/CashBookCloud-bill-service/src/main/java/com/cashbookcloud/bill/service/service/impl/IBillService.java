package com.cashbookcloud.bill.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cashbookcloud.bill.api.dto.BillDto;
import com.cashbookcloud.bill.api.dto.KeepingDto;
import com.cashbookcloud.bill.api.service.BillService;
import com.cashbookcloud.bill.service.covert.BillCovert;
import com.cashbookcloud.bill.service.entity.Bill;
import com.cashbookcloud.bill.service.mapper.BillMapper;
import com.cashbookcloud.billlist.api.dto.BilllistDto;
import com.cashbookcloud.billlist.api.service.BilllistService;
import com.cashbookcloud.cat.api.dto.CatDto;
import com.cashbookcloud.cat.api.service.CatService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.checkerframework.checker.units.qual.A;
import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@org.springframework.stereotype.Service
public class IBillService implements BillService {

    @Autowired
    private BillMapper billMapper;

    @Reference
    private CatService catService;

    @Reference
    private BilllistService billlistService;

    @Override
    public Page<BillDto> findAllPage(Integer pagenum, Integer pagesize,String query) {
        Page<Bill> page = new Page<>(pagenum, pagesize);
        if(query == null || query.equals("")){
            Page<Bill> billPage = billMapper.selectPage(page, null);
            Page<BillDto> billDtoPage = new Page<>();
            List<BillDto> billDtos = new ArrayList<>();
            for (int i = 0; i < billPage.getRecords().size(); i++) {
                BillDto billDto = BillCovert.INSTANCE.entity2dto(billPage.getRecords().get(i));
                CatDto catById = catService.findCatById(billDto.getCatId());
                billDto.setCatName(catById.getCatName());
                BilllistDto byId = billlistService.findById(billDto.getBilllistId());
                billDto.setBilllistName(byId.getBilllistName());
                billDtos.add(billDto);
            }
            billDtoPage.setRecords(billDtos);
            billDtoPage.setTotal(billPage.getTotal());
            billDtoPage.setCountId(billPage.getCountId());
            billDtoPage.setCurrent(billPage.getCurrent());
            billDtoPage.setMaxLimit(billPage.getMaxLimit());
            billDtoPage.setSize(billPage.getSize());
            billDtoPage.setPages(billPage.getPages());
            return billDtoPage;
        }else {
            QueryWrapper<Bill> wrapper = new QueryWrapper<>();
            wrapper.like("billPrice",query).or().like("billName",query).or().like("billDate",query);
            Page<Bill> billPage = billMapper.selectPage(page, wrapper);
            Page<BillDto> billDtoPage = new Page<>();
            List<BillDto> billDtos = new ArrayList<>();
            for (int i = 0; i < billPage.getRecords().size(); i++) {
                BillDto billDto = BillCovert.INSTANCE.entity2dto(billPage.getRecords().get(i));
                CatDto catById = catService.findCatById(billDto.getCatId());
                billDto.setCatName(catById.getCatName());
                BilllistDto byId = billlistService.findById(billDto.getBilllistId());
                billDto.setBilllistName(byId.getBilllistName());
                billDtos.add(billDto);
            }
            billDtoPage.setRecords(billDtos);
            billDtoPage.setTotal(billPage.getTotal());
            billDtoPage.setCountId(billPage.getCountId());
            billDtoPage.setCurrent(billPage.getCurrent());
            billDtoPage.setMaxLimit(billPage.getMaxLimit());
            billDtoPage.setSize(billPage.getSize());
            billDtoPage.setPages(billPage.getPages());
            return billDtoPage;
        }

    }

    @Override
    public BillDto addBill(BillDto billDto) {
        Bill bill = BillCovert.INSTANCE.dto2entity(billDto);
        billMapper.insert(bill);
        BillDto billDto1 = BillCovert.INSTANCE.entity2dto(bill);
        return billDto1;
    }

    @Override
    public void deleteBillById(Integer id) {
        billMapper.deleteById(id);
    }

    @Override
    public BillDto selectById(Integer id) {
        Bill bill = billMapper.selectById(id);
        BillDto billDto = BillCovert.INSTANCE.entity2dto(bill);
        return billDto;
    }

    @Override
    public BillDto updateById(BillDto billDto) {
        Bill bill = BillCovert.INSTANCE.dto2entity(billDto);
        int i = billMapper.updateById(bill);
        BillDto dto = BillCovert.INSTANCE.entity2dto(bill);
        return dto;
    }

    @Override
    public KeepingDto getKeeping(Integer id) {
        QueryWrapper<Bill> wrapper = new QueryWrapper<>();
        wrapper.eq("billlist_id",id).and(w -> w.eq("bill_describe","支出"));
        List<Bill> zhichubills = billMapper.selectList(wrapper);

        QueryWrapper<Bill> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("billlist_id",id).and(w -> w.eq("bill_describe","收入"));
        List<Bill> shourubills = billMapper.selectList(wrapper1);

        Double zhichu = 0.0;
        Double shouru = 0.0;
        Double jieyu = 0.0;

        if(zhichubills.size() == 0 || zhichubills==null){
            if(shourubills.size() == 0 || shourubills == null){

                KeepingDto keepingDto = new KeepingDto();
                keepingDto.setJieyu(jieyu);
                keepingDto.setShouru(shouru);
                keepingDto.setZhichu(zhichu);
                return keepingDto;
            }
            else {
                for (int i = 0; i < shourubills.size(); i++) {
                    shouru = shouru + shourubills.get(i).getBillPrice();
                }
                KeepingDto keepingDto = new KeepingDto();
                keepingDto.setJieyu(shouru);
                keepingDto.setShouru(shouru);
                keepingDto.setZhichu(zhichu);
                return keepingDto;
            }
        }else {
            if(shourubills.size() == 0 || shourubills == null){
                for (int i = 0; i < zhichubills.size(); i++) {
                    zhichu = zhichu + zhichubills.get(i).getBillPrice();
                }
                KeepingDto keepingDto = new KeepingDto();
                keepingDto.setJieyu(jieyu-zhichu);
                keepingDto.setShouru(shouru);
                keepingDto.setZhichu(zhichu);
                return keepingDto;
            }
            else {
                for (int i = 0; i < zhichubills.size(); i++) {
                    zhichu = zhichu + zhichubills.get(i).getBillPrice();
                }
                for (int i = 0; i < shourubills.size(); i++) {
                    shouru = shouru + shourubills.get(i).getBillPrice();
                }
                KeepingDto keepingDto = new KeepingDto();
                keepingDto.setJieyu(shouru-zhichu);
                keepingDto.setShouru(shouru);
                keepingDto.setZhichu(zhichu);
                return keepingDto;
            }
        }
    }
}
