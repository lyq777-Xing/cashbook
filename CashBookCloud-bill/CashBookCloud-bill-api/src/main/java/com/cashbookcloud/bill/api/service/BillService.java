package com.cashbookcloud.bill.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cashbookcloud.bill.api.dto.BillDto;
import io.swagger.models.auth.In;

public interface BillService {
    /**
     * 获取所有支出收入
     * @param pagenum
     * @param pagesize
     * @return
     */
    Page<BillDto> findAllPage(Integer pagenum, Integer pagesize,String query);

    /**
     * 添加账单
     * @param billDto
     * @return
     */
    BillDto addBill(BillDto billDto);

    /**
     * 根据id删除账单
     * @param id
     */
    void deleteBillById(Integer id);

    /**
     * 根据id查询账单
     * @param id
     * @return
     */
    BillDto selectById(Integer id);

    /**
     * 根据id更新账单
     * @param billDto
     * @return
     */
    BillDto updateById(BillDto billDto);
}
