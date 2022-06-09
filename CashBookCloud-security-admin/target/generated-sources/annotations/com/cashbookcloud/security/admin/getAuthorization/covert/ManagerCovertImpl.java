package com.cashbookcloud.security.admin.getAuthorization.covert;

import com.cashbookcloud.security.admin.getAuthorization.dto.ManagerDto;
import com.cashbookcloud.security.admin.getAuthorization.entity.Manager;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-08T22:21:33+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_302 (Amazon.com Inc.)"
)
public class ManagerCovertImpl implements ManagerCovert {

    @Override
    public ManagerDto entity2dto(Manager entity) {
        if ( entity == null ) {
            return null;
        }

        ManagerDto managerDto = new ManagerDto();

        managerDto.setId( entity.getId() );
        managerDto.setMgName( entity.getMgName() );
        managerDto.setMgPassword( entity.getMgPassword() );
        managerDto.setRoleId( entity.getRoleId() );
        managerDto.setMgPhone( entity.getMgPhone() );
        managerDto.setMgDate( entity.getMgDate() );
        managerDto.setMgEmail( entity.getMgEmail() );
        managerDto.setMgHeader( entity.getMgHeader() );

        return managerDto;
    }

    @Override
    public Manager dto2entity(ManagerDto dto) {
        if ( dto == null ) {
            return null;
        }

        Manager manager = new Manager();

        manager.setId( dto.getId() );
        manager.setMgName( dto.getMgName() );
        manager.setMgPassword( dto.getMgPassword() );
        manager.setRoleId( dto.getRoleId() );
        manager.setMgPhone( dto.getMgPhone() );
        manager.setMgDate( dto.getMgDate() );
        manager.setMgEmail( dto.getMgEmail() );
        manager.setMgHeader( dto.getMgHeader() );

        return manager;
    }
}
