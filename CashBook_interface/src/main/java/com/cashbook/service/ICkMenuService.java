package com.cashbook.service;

import com.cashbook.entity.CkMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2022-04-30
 */
public interface ICkMenuService extends IService<CkMenu> {
    List<CkMenu> findMenusTree();
}
