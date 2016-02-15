package com.excilys.computerdatabase.dto.mapper.impl;

import com.excilys.computerdatabase.dto.mapper.UserDetailDtoMapper;
import com.excilys.computerdatabase.dto.model.UserDetailDto;
import com.excilys.computerdatabase.model.Role;
import com.excilys.computerdatabase.model.UserDetail;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * @author Vincent Galloy
 *         The Class UserDetailDtoMapperImpl.
 */
@Service
public class UserDetailDtoMapperImpl implements UserDetailDtoMapper {

    @Override
    public UserDetailDto mapFromModel(UserDetail model) {
        UserDetailDto userDetailDto = new UserDetailDto();
        userDetailDto.setUserName(model.getUserName());
        userDetailDto.setRoles(model.getRoles().stream().map(e -> e.toString()).collect(Collectors.toList()));
        return userDetailDto;
    }

    @Override
    public UserDetail mapToModel(UserDetailDto dto) {
        UserDetail userDetail = new UserDetail();
        userDetail.setUserName(dto.getUserName());
        userDetail.setPassword(dto.getPassword());
        if (dto.getRoles() != null) {
            userDetail.setRoles(dto.getRoles().stream().map(Role::build).collect(Collectors.toSet()));
        }
        return userDetail;
    }
}
