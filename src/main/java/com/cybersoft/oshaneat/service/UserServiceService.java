package com.cybersoft.oshaneat.service;

import com.cybersoft.oshaneat.dto.UserDTO;
import com.cybersoft.oshaneat.entity.Users;
import com.cybersoft.oshaneat.repository.UserRepository;
import com.cybersoft.oshaneat.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceService implements UserServiceImp {

    @Autowired
    UserRepository userRepository;

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
}
