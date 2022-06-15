package com.cashbookcloud.permission.service.covert;

import com.cashbookcloud.permission.api.dto.PermissionDto;
import com.cashbookcloud.permission.service.entity.Permission;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-15T14:05:32+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_302 (Amazon.com Inc.)"
)
public class PermissionCovertImpl implements PermissionCovert {

    @Override
    public PermissionDto entity2dto(Permission entity) {
        if ( entity == null ) {
            return null;
        }

        PermissionDto permissionDto = new PermissionDto();

        permissionDto.setId( entity.getId() );
        permissionDto.setPermissionName( entity.getPermissionName() );
        permissionDto.setPermissionLevel( entity.getPermissionLevel() );
        permissionDto.setPermissionPath( entity.getPermissionPath() );
        permissionDto.setPermissionPid( entity.getPermissionPid() );

        return permissionDto;
    }

    @Override
    public Permission dto2entity(PermissionDto dto) {
        if ( dto == null ) {
            return null;
        }

        Permission permission = new Permission();

        permission.setId( dto.getId() );
        permission.setPermissionName( dto.getPermissionName() );
        permission.setPermissionLevel( dto.getPermissionLevel() );
        permission.setPermissionPath( dto.getPermissionPath() );
        permission.setPermissionPid( dto.getPermissionPid() );

        return permission;
    }
}
