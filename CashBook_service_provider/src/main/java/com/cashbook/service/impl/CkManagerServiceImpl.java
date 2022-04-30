package com.cashbook.service.impl;

import com.cashbook.entity.CkManager;
import com.cashbook.mapper.CkManagerMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cashbook.service.ICkManagerService;
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
public class CkManagerServiceImpl extends ServiceImpl<CkManagerMapper, CkManager> implements ICkManagerService {

}
