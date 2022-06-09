package com.cashbookcloud.user.service.covert;

import com.cashbookcloud.user.api.dto.UserDto;
import com.cashbookcloud.user.service.entity.User;
import com.cashbookcloud.user.service.vo.UserVo;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-08T22:16:18+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_302 (Amazon.com Inc.)"
)
public class UserCovertImpl implements UserCovert {

    @Override
    public UserDto entity2dto(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( entity.getId() );
        userDto.setUserName( entity.getUserName() );
        userDto.setUserPassword( entity.getUserPassword() );
        userDto.setUserPhone( entity.getUserPhone() );
        userDto.setUserCreatedate( entity.getUserCreatedate() );
        userDto.setRoleId( entity.getRoleId() );
        userDto.setUserHeader( entity.getUserHeader() );
        userDto.setUserEmail( entity.getUserEmail() );

        return userDto;
    }

    @Override
    public User dto2entity(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setId( dto.getId() );
        user.setUserName( dto.getUserName() );
        user.setUserPassword( dto.getUserPassword() );
        user.setUserPhone( dto.getUserPhone() );
        user.setUserCreatedate( dto.getUserCreatedate() );
        user.setRoleId( dto.getRoleId() );
        user.setUserHeader( dto.getUserHeader() );
        user.setUserEmail( dto.getUserEmail() );

        return user;
    }

    @Override
    public UserVo dto2vo(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserVo userVo = new UserVo();

        userVo.setId( dto.getId() );
        userVo.setUserName( dto.getUserName() );
        userVo.setUserPassword( dto.getUserPassword() );
        userVo.setUserPhone( dto.getUserPhone() );
        userVo.setUserCreatedate( dto.getUserCreatedate() );
        userVo.setRoleId( dto.getRoleId() );
        userVo.setUserHeader( dto.getUserHeader() );
        userVo.setRoleName( dto.getRoleName() );
        userVo.setUserEmail( dto.getUserEmail() );

        return userVo;
    }

    @Override
    public UserDto vo2dto(UserVo vo) {
        if ( vo == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( vo.getId() );
        userDto.setUserName( vo.getUserName() );
        userDto.setUserPassword( vo.getUserPassword() );
        userDto.setUserPhone( vo.getUserPhone() );
        userDto.setUserCreatedate( vo.getUserCreatedate() );
        userDto.setRoleId( vo.getRoleId() );
        userDto.setUserHeader( vo.getUserHeader() );
        userDto.setRoleName( vo.getRoleName() );
        userDto.setUserEmail( vo.getUserEmail() );

        return userDto;
    }
}
