package com.cybersoft.oshaneat.service.imp;

import com.cybersoft.oshaneat.dto.UserDTO;
import com.cybersoft.oshaneat.payload.request.SignUpRequest;

import java.util.List;

public interface LoginServiceImp {
    List<UserDTO> getAllUser();
    boolean checkLogin(String username, String password);
    boolean addUser(SignUpRequest signUpRequest);

}
