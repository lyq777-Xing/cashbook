package com.cashbookcloud.role.service.covert;

import com.cashbookcloud.role.api.dto.RoleDto;
import com.cashbookcloud.role.service.entity.Role;
import com.cashbookcloud.role.service.vo.RoleVo;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-12T10:13:45+0800",
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

    @Override
    public RoleVo dto2vo(RoleDto dto) {
        if ( dto == null ) {
            return null;
        }

        RoleVo roleVo = new RoleVo();

        roleVo.setId( dto.getId() );
        roleVo.setRoleName( dto.getRoleName() );
        roleVo.setRoleKeyword( dto.getRoleKeyword() );
        roleVo.setRoleDescribe( dto.getRoleDescribe() );
        roleVo.setRolePojo( dto.getRolePojo() );

        return roleVo;
    }

    @Override
    public RoleDto vo2dto(RoleVo vo) {
        if ( vo == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setId( vo.getId() );
        roleDto.setRoleName( vo.getRoleName() );
        roleDto.setRoleKeyword( vo.getRoleKeyword() );
        roleDto.setRoleDescribe( vo.getRoleDescribe() );
        roleDto.setRolePojo( vo.getRolePojo() );

        return roleDto;
    }
}
