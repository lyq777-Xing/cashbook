package com.cashbookcloud.bill.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cashbookcloud.bill.api.dto.*;
import com.cashbookcloud.bill.api.service.BillService;
import com.cashbookcloud.bill.service.client.BilllistClient;
import com.cashbookcloud.bill.service.client.CatClient;
import com.cashbookcloud.bill.service.covert.BillCovert;
import com.cashbookcloud.bill.service.dto.BilllistDto;
import com.cashbookcloud.bill.service.dto.CatDto;
import com.cashbookcloud.bill.service.entity.Bill;
import com.cashbookcloud.bill.service.mapper.BillMapper;
import com.cashbookcloud.common.result.ResponseResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.dubbo.config.annotation.Service;
import org.checkerframework.checker.units.qual.A;
import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Transactional
@Service
@org.springframework.stereotype.Service
public class IBillService implements BillService {

    @Autowired
    private BillMapper billMapper;

//    @Reference
//    private CatService catService;
//
//    @Reference
//    private BilllistService billlistService;

    @Autowired
    private CatClient catClient;

    @Autowired
    private BilllistClient billlistClient;

    @Override
    public Page<BillDto> findAllPage(Integer pagenum, Integer pagesize,String query) {
        Page<Bill> page = new Page<>(pagenum, pagesize);
        if(query == null || query.equals("")){
            Page<Bill> billPage = billMapper.selectPage(page, null);
            Page<BillDto> billDtoPage = new Page<>();
            List<BillDto> billDtos = new ArrayList<>();
            for (int i = 0; i < billPage.getRecords().size(); i++) {
                BillDto billDto = BillCovert.INSTANCE.entity2dto(billPage.getRecords().get(i));
                ResponseResult result = catClient.findById(billDto.getCatId());
                // 将数据转成json字符串
                JSONObject jsonObject= JSONObject.fromObject(result.getData());
                //将json转成需要的对象
                CatDto catDto = (CatDto)JSONObject.toBean(jsonObject, CatDto.class);
                billDto.setCatName(catDto.getCatName());
                ResponseResult result1 = billlistClient.findById(billDto.getCatId());
                // 将数据转成json字符串
                JSONObject jsonObject1= JSONObject.fromObject(result.getData());
                //将json转成需要的对象
                BilllistDto byId = (BilllistDto)JSONObject.toBean(jsonObject1, BilllistDto.class);
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
                ResponseResult result = catClient.findById(billDto.getCatId());
                // 将数据转成json字符串
                JSONObject jsonObject= JSONObject.fromObject(result.getData());
                //将json转成需要的对象
                CatDto catDto = (CatDto)JSONObject.toBean(jsonObject, CatDto.class);
                billDto.setCatName(catDto.getCatName());
                ResponseResult result1 = billlistClient.findById(billDto.getBilllistId());
                // 将数据转成json字符串
                JSONObject jsonObject1= JSONObject.fromObject(result1.getData());
                //将json转成需要的对象
                BilllistDto byId = (BilllistDto)JSONObject.toBean(jsonObject1, BilllistDto.class);
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
        ResponseResult result = catClient.findById(billDto.getCatId());
        JSONObject jsonObject = JSONObject.fromObject(result.getData());
        CatDto catDto= (CatDto) JSONObject.toBean(jsonObject, CatDto.class);
        billDto.setCatName(catDto.getCatName());
        ResponseResult result1 = billlistClient.findById(billDto.getBilllistId());
        // 将数据转成json字符串
        JSONObject jsonObject1= JSONObject.fromObject(result1.getData());
        //将json转成需要的对象
        BilllistDto byId = (BilllistDto)JSONObject.toBean(jsonObject1, BilllistDto.class);
        billDto.setBilllistName(byId.getBilllistName());
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

    @Override
    public Integer getCount(Integer billlistId) {
        QueryWrapper<Bill> wrapper = new QueryWrapper<>();
        wrapper.eq("billlist_id",billlistId);
        Integer aLong = Math.toIntExact(billMapper.selectCount(wrapper));
        return aLong;
    }

    @Override
    public List<BillDto> findAllByUserIdAndBilllistId(Integer userId, Integer billlistId) {
        QueryWrapper<Bill> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId).eq("billlist_id",billlistId);
        List<Bill> bills = billMapper.selectList(wrapper);
        ArrayList<BillDto> billDtos = new ArrayList<>();
        for (Bill b:bills) {
            BillDto billDto = BillCovert.INSTANCE.entity2dto(b);
            Integer catId = billDto.getCatId();
            ResponseResult result = catClient.findById(catId);
            JSONObject jsonObject = JSONObject.fromObject(result.getData());
            CatDto catDto= (CatDto) JSONObject.toBean(jsonObject, CatDto.class);
//            ResponseResult result = catClient.findById(billDto.getCatId());
            // 将数据转成json字符串
//            JSONObject jsonObject= JSONObject.fromObject(result.getData());
            //将json转成需要的对象
//            CatDto catDto = (CatDto)JSONObject.toBean(jsonObject, CatDto.class);
            billDto.setCatImg(catDto.getCatImg());
            billDto.setCatName(catDto.getCatName());
            billDtos.add(billDto);
        }
        return billDtos;
    }

    @Override
    public CashReportDto getReportOne(Integer userId) {
        CashReportDto cashReportDto = new CashReportDto();
        Date date = new Date();
        int daysOfMonth = getDaysOfMonth(date);
        if(daysOfMonth == 31){
            cashReportDto.setX(DateConstant.ThirtyAndOne_DAY);
        }else if(daysOfMonth == 30){
            cashReportDto.setX(DateConstant.Thirty_DAY);
        }else if(daysOfMonth == 28){
            cashReportDto.setX(DateConstant.TewentyAndEight_DAY);
        }else if(daysOfMonth == 29){
            cashReportDto.setX(DateConstant.TewentyAndNine_DAY);
        }
        ArrayList<Double> list = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        String string = new SimpleDateFormat("yyyy-MM").format(new Date()).toString();
        for (int i = 0; i < cashReportDto.getX().size(); i++) {
            int k = i + 1;
            String m = string + "-" + k;
            QueryWrapper<Bill> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id",userId).eq("bill_date",m).eq("bill_describe","支出");
            List<Bill> bills = billMapper.selectList(wrapper);
            if (bills.size() == 0 || bills == null){
//                表明当天未记账
                list.add(0.0);
            }else {
                Double sum = 0.0;
                for (Bill b :bills) {
                    Double billPrice = b.getBillPrice();
                    sum = sum + billPrice;
                }
                list.add(sum);
            }
        }
        cashReportDto.setY(list);
        return cashReportDto;
    }

    @Override
    public List<CatReportDto> getReportTwo(Integer userId) {
        Date date = new Date();
//        获取当前月有多少天
        int daysOfMonth = getDaysOfMonth(date);
//        获取当前年月
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
//        将当前年月转化为字符串格式
        String string = new SimpleDateFormat("yyyy-MM").format(new Date()).toString();
        String max = string + "-" + daysOfMonth;
        String min = string + "-1";

        ArrayList<CatReportDto> catReportDtos = new ArrayList<>();

        ResponseResult result = catClient.getAllCats();
//        ArrayList<CatDto> catDtos = new ArrayList<>();
        JSONArray jsonArray = JSONArray.fromObject(result.getData());
        for (int i = 0; i < jsonArray.size(); i++) {
            CatDto catDto = (CatDto) JSONObject.toBean(jsonArray.getJSONObject(i), CatDto.class);
            QueryWrapper<Bill> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id",userId).lt("bill_date",max).ge("bill_date",min).eq("cat_id",catDto.getId()).eq("bill_describe","支出");
            List<Bill> bills = billMapper.selectList(wrapper);
//            说明该月并无此分类的账单
            if(bills.size() == 0 || bills == null){
                CatReportDto catReportDto = new CatReportDto();
                catReportDto.setName(catDto.getCatName());
                catReportDto.setValue(0.0);
                catReportDtos.add(catReportDto);
            }else {
//                说明该月有此分类的账单
                Double sum = 0.0;
                for (Bill b:bills) {
                    sum = sum + b.getBillPrice();
                }
                CatReportDto catReportDto = new CatReportDto();
                catReportDto.setName(catDto.getCatName());
                catReportDto.setValue(sum);
                catReportDtos.add(catReportDto);
            }
        }
        return catReportDtos;
    }

    @Override
    public void delByCatId(Integer catId) {
        QueryWrapper<Bill> wrapper = new QueryWrapper<>();
        wrapper.eq("cat_id",catId);
        billMapper.delete(wrapper);
    }

    @Override
    public List<BillDto> getReportThree(Integer userId, Integer billlistId) {
        QueryWrapper<Bill> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId).eq("billlist_id",billlistId);
        List<Bill> bills = billMapper.selectList(wrapper);
        ArrayList<BillDto> billDtos = new ArrayList<>();
        for (int i = 0; i < bills.size(); i++) {
            Integer catId = bills.get(i).getCatId();
            ResponseResult byId = catClient.findById(catId);
            JSONObject jsonObject = JSONObject.fromObject(byId.getData());
            CatDto catDto = (CatDto) JSONObject.toBean(jsonObject, CatDto.class);
            String catName = catDto.getCatName();
            BillDto billDto = BillCovert.INSTANCE.entity2dto(bills.get(i));
            billDto.setCatName(catName);
            billDtos.add(billDto);
        }
        return billDtos;
    }

    /**
     * 获取一个月天数
     * @param date
     * @return
     */
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}
