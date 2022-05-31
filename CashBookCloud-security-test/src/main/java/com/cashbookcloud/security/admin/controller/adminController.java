package com.cashbookcloud.security.admin.controller;

import com.cashbookcloud.common.result.ResponseResult;
import com.cashbookcloud.security.admin.dto.ManagerDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class adminController {

    @GetMapping("/test")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseResult test(){
        ResponseResult<Object> result = new ResponseResult<>();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        result.Success("ok!",principal);
        return result;
    }
}
