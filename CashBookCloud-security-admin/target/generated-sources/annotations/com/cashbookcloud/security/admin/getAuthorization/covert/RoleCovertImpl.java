package com.cashbookcloud.security.admin.getAuthorization.covert;

import com.cashbookcloud.security.admin.getAuthorization.dto.RoleDto;
import com.cashbookcloud.security.admin.getAuthorization.entity.Role;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-05T19:25:04+0800",
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
        roleDto.setRolePojo( entity.getRolePojo() );

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
        role.setRolePojo( dto.getRolePojo() );

        return role;
    }
}
