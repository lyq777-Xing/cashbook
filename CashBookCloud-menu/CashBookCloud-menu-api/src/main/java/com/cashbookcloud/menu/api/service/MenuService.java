package com.cashbookcloud.menu.api.service;

import com.cashbookcloud.menu.api.dto.MenuDto;

import java.util.List;

public interface MenuService {
    List<MenuDto> findMenusTree();
}
