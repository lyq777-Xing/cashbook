package com.cashbook.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cashbook.entity.CkMenu;
import com.cashbook.mapper.CkMenuMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cashbook.service.ICkMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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
public class CkMenuServiceImpl extends ServiceImpl<CkMenuMapper, CkMenu> implements ICkMenuService {

    @Autowired
    private CkMenuMapper menuMapper;

    /**
     * 查询菜单（树形结构）
     * @return
     */
    @Override
    public List<CkMenu> findMenusTree() {
        QueryWrapper<CkMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("menu_level",0);
        List<CkMenu> ckMenus = menuMapper.selectList(wrapper);
        QueryWrapper<CkMenu> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("menu_level",1);
        List<CkMenu> ckMenus1 = menuMapper.selectList(wrapper1);
        for (CkMenu first:ckMenus) {
            ArrayList<CkMenu> list = new ArrayList<>();
            for (CkMenu two:ckMenus1) {
                if(two.getMenuPid().intValue() == first.getId().intValue()){
                    list.add(two);
                }
            }
            first.setChildren(list);
        }
        return ckMenus;
    }
}
