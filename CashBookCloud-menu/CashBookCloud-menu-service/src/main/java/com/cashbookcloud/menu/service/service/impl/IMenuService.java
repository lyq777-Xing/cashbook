package com.cashbookcloud.menu.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cashbookcloud.menu.api.dto.MenuDto;
import com.cashbookcloud.menu.api.service.MenuService;
import com.cashbookcloud.menu.service.convert.MenuCovert;
import com.cashbookcloud.menu.service.entity.Menu;
import com.cashbookcloud.menu.service.mapper.MenuMapper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@org.apache.dubbo.config.annotation.Service
public class IMenuService implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 查询菜单（树形结构）
     * @return
     */
    @Override
    public List<MenuDto> findMenusTree() {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.eq("menu_level",0);
        List<Menu> ckMenus = menuMapper.selectList(wrapper);
        ArrayList<MenuDto> menuDtos = new ArrayList<>();
        for (int i = 0; i < ckMenus.size(); i++) {
            MenuDto menuDto = MenuCovert.INSTANCE.entity2dto(ckMenus.get(i));
            menuDtos.add(menuDto);
        }
        QueryWrapper<Menu> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("menu_level",1);
        List<Menu> ckMenus1 = menuMapper.selectList(wrapper1);
        ArrayList<MenuDto> menuDtos1 = new ArrayList<>();
        for (int i = 0; i < ckMenus1.size(); i++) {
            MenuDto menuDto = MenuCovert.INSTANCE.entity2dto(ckMenus1.get(i));
            menuDtos1.add(menuDto);
        }

        for (MenuDto first:menuDtos) {
            ArrayList<MenuDto> list = new ArrayList<>();
            for (MenuDto two:menuDtos1) {
                if(two.getMenuPid().intValue() == first.getId().intValue()){
                    list.add(two);
                }
            }
            first.setChildren(list);
        }
        return menuDtos;
    }
}
