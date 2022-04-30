package com.cashbook.service.impl;

import com.cashbook.entity.CkBilllist;
import com.cashbook.mapper.CkBilllistMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cashbook.service.ICkBilllistService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2022-04-30
 */
@Slf4j
@Service
@Transactional
public class CkBilllistServiceImpl extends ServiceImpl<CkBilllistMapper, CkBilllist> implements ICkBilllistService {

}
