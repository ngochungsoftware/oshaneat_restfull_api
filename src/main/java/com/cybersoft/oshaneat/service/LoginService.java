package com.cybersoft.oshaneat.service;

import com.cybersoft.oshaneat.dto.UserDTO;
import com.cybersoft.oshaneat.entity.Roles;
import com.cybersoft.oshaneat.entity.Users;
import com.cybersoft.oshaneat.payload.request.SignUpRequest;
import com.cybersoft.oshaneat.repository.UserRepository;
import com.cybersoft.oshaneat.service.imp.LoginServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService implements LoginServiceImp {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> getAllUser() {
        List<Users> listUser = userRepository.findAll();
        List<UserDTO> listUserDTO = new ArrayList<>();
        for (Users users : listUser) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(users.getId());
            userDTO.setUsername(users.getUsername());
            userDTO.setPassword(users.getPassword());
            userDTO.setFullName(users.getFullName());
            listUserDTO.add(userDTO);
//            System.out.println(users.getFullName());
        }
        return listUserDTO;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        Users user = userRepository.findByUsername(username); // user entity
        return passwordEncoder.matches(password,user.getPassword());
    }

    @Override
    public boolean addUser(SignUpRequest signUpRequest) {
        Users users = new Users();
        Roles roles = new Roles();
        roles.setId(signUpRequest.getRoleId());
        users.setFullName(signUpRequest.getFullName());
        users.setUsername(signUpRequest.getEmail());
        users.setPassword(signUpRequest.getPassword());
        users.setRoles(roles);
        try {
            userRepository.save(users);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
