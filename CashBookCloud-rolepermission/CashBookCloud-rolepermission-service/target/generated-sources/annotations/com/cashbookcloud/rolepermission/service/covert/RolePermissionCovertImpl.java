package com.cashbookcloud.rolepermission.service.covert;

import com.cashbookcloud.rolepermission.api.dto.RolePermissionDto;
import com.cashbookcloud.rolepermission.service.entity.RolePermission;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-18T13:09:02+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_211 (Oracle Corporation)"
)
public class RolePermissionCovertImpl implements RolePermissionCovert {

    @Override
    public RolePermissionDto entity2dto(RolePermission entity) {
        if ( entity == null ) {
            return null;
        }

        RolePermissionDto rolePermissionDto = new RolePermissionDto();

        rolePermissionDto.setRoleId( entity.getRoleId() );
        rolePermissionDto.setPermissionId( entity.getPermissionId() );

        return rolePermissionDto;
    }

    @Override
    public RolePermission dto2entity(RolePermissionDto dto) {
        if ( dto == null ) {
            return null;
        }

        RolePermission rolePermission = new RolePermission();

        rolePermission.setRoleId( dto.getRoleId() );
        rolePermission.setPermissionId( dto.getPermissionId() );

        return rolePermission;
    }
}
