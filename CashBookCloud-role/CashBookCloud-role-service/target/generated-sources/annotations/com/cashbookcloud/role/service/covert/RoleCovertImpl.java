package com.cashbookcloud.role.service.covert;

import com.cashbookcloud.role.api.dto.RoleDto;
import com.cashbookcloud.role.service.entity.Role;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-23T19:57:46+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_302 (Amazon.com Inc.)"
)
public class RoleCovertImpl implements RoleCovert {

    @Override
    public RoleDto entity2dto(Role entity) {
        if ( entity == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setId( entity.getId() );
        roleDto.setRoleName( entity.getRoleName() );
        roleDto.setRoleKeyword( entity.getRoleKeyword() );
        roleDto.setRoleDescribe( entity.getRoleDescribe() );

        return roleDto;
    }

    @Override
    public Role dto2entity(RoleDto dto) {
        if ( dto == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( dto.getId() );
        role.setRoleName( dto.getRoleName() );
        role.setRoleKeyword( dto.getRoleKeyword() );
        role.setRoleDescribe( dto.getRoleDescribe() );

        return role;
    }
}
