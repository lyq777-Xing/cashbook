package com.cashbookcloud.security.user.getAuthorization.covert;

import com.cashbookcloud.security.user.getAuthorization.dto.UserDto;
import com.cashbookcloud.security.user.getAuthorization.entity.User;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-22T10:05:36+0800",
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
}
