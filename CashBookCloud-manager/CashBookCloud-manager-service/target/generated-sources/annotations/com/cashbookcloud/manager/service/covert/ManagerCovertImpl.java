package com.cashbookcloud.manager.service.covert;

import com.cashbookcloud.manager.api.dto.ManagerDto;
import com.cashbookcloud.manager.service.entity.Manager;
import com.cashbookcloud.manager.service.vo.ManagerVo;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-13T16:47:24+0800",
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

    @Override
    public ManagerVo dto2vo(ManagerDto dto) {
        if ( dto == null ) {
            return null;
        }

        ManagerVo managerVo = new ManagerVo();

        managerVo.setId( dto.getId() );
        managerVo.setMgName( dto.getMgName() );
        managerVo.setMgPassword( dto.getMgPassword() );
        managerVo.setRoleId( dto.getRoleId() );
        managerVo.setMgPhone( dto.getMgPhone() );
        managerVo.setMgDate( dto.getMgDate() );
        managerVo.setMgEmail( dto.getMgEmail() );
        managerVo.setMgHeader( dto.getMgHeader() );
        managerVo.setRoleName( dto.getRoleName() );

        return managerVo;
    }

    @Override
    public ManagerDto vo2dto(ManagerVo vo) {
        if ( vo == null ) {
            return null;
        }

        ManagerDto managerDto = new ManagerDto();

        managerDto.setId( vo.getId() );
        managerDto.setMgName( vo.getMgName() );
        managerDto.setMgPassword( vo.getMgPassword() );
        managerDto.setRoleId( vo.getRoleId() );
        managerDto.setMgPhone( vo.getMgPhone() );
        managerDto.setMgDate( vo.getMgDate() );
        managerDto.setMgEmail( vo.getMgEmail() );
        managerDto.setMgHeader( vo.getMgHeader() );
        managerDto.setRoleName( vo.getRoleName() );

        return managerDto;
    }
}
