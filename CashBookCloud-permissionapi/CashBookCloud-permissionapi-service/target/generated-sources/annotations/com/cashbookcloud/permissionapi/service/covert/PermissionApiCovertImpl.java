package com.cashbookcloud.permissionapi.service.covert;

import com.cashbookcloud.permissionapi.api.dto.PermissionApiDto;
import com.cashbookcloud.permissionapi.service.entity.PermissionApi;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-09T13:22:39+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_302 (Amazon.com Inc.)"
)
public class PermissionApiCovertImpl implements PermissionApiCovert {

    @Override
    public PermissionApiDto entity2dto(PermissionApi entity) {
        if ( entity == null ) {
            return null;
        }

        PermissionApiDto permissionApiDto = new PermissionApiDto();

        permissionApiDto.setId( entity.getId() );
        permissionApiDto.setPermissionId( entity.getPermissionId() );
        permissionApiDto.setPermissionKeyword( entity.getPermissionKeyword() );

        return permissionApiDto;
    }

    @Override
    public PermissionApi dto2entity(PermissionApiDto dto) {
        if ( dto == null ) {
            return null;
        }

        PermissionApi permissionApi = new PermissionApi();

        permissionApi.setId( dto.getId() );
        permissionApi.setPermissionId( dto.getPermissionId() );
        permissionApi.setPermissionKeyword( dto.getPermissionKeyword() );

        return permissionApi;
    }
}
