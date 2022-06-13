package com.cashbookcloud.menu.service.convert;

import com.cashbookcloud.menu.api.dto.MenuDto;
import com.cashbookcloud.menu.service.entity.Menu;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-13T16:47:15+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_302 (Amazon.com Inc.)"
)
public class MenuCovertImpl implements MenuCovert {

    @Override
    public MenuDto entity2dto(Menu entity) {
        if ( entity == null ) {
            return null;
        }

        MenuDto menuDto = new MenuDto();

        menuDto.setId( entity.getId() );
        menuDto.setMenuName( entity.getMenuName() );
        menuDto.setMenuLevel( entity.getMenuLevel() );
        menuDto.setMenuPid( entity.getMenuPid() );
        menuDto.setMenuPath( entity.getMenuPath() );
        menuDto.setChildren( menuListToMenuDtoList( entity.getChildren() ) );

        return menuDto;
    }

    @Override
    public Menu dto2entity(MenuDto dto) {
        if ( dto == null ) {
            return null;
        }

        Menu menu = new Menu();

        menu.setId( dto.getId() );
        menu.setMenuName( dto.getMenuName() );
        menu.setMenuLevel( dto.getMenuLevel() );
        menu.setMenuPid( dto.getMenuPid() );
        menu.setMenuPath( dto.getMenuPath() );
        menu.setChildren( menuDtoListToMenuList( dto.getChildren() ) );

        return menu;
    }

    protected List<MenuDto> menuListToMenuDtoList(List<Menu> list) {
        if ( list == null ) {
            return null;
        }

        List<MenuDto> list1 = new ArrayList<MenuDto>( list.size() );
        for ( Menu menu : list ) {
            list1.add( entity2dto( menu ) );
        }

        return list1;
    }

    protected List<Menu> menuDtoListToMenuList(List<MenuDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Menu> list1 = new ArrayList<Menu>( list.size() );
        for ( MenuDto menuDto : list ) {
            list1.add( dto2entity( menuDto ) );
        }

        return list1;
    }
}
