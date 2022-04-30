package com.cashbook.service.impl;

import com.cashbook.entity.CkPermiision;
import com.cashbook.mapper.CkPermiisionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cashbook.service.ICkPermiisionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

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
public class CkPermiisionServiceImpl extends ServiceImpl<CkPermiisionMapper, CkPermiision> implements ICkPermiisionService {

}
