package com.cashbook.service.impl;

import com.cashbook.entity.CkRole;
import com.cashbook.mapper.CkRoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cashbook.service.ICkRoleService;
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
public class CkRoleServiceImpl extends ServiceImpl<CkRoleMapper, CkRole> implements ICkRoleService {

}
