package com.cybersoft.oshaneat.controller;


import com.cybersoft.oshaneat.payload.ResponseData;
import com.cybersoft.oshaneat.payload.request.SignUpRequest;
import com.cybersoft.oshaneat.service.imp.LoginServiceImp;
import com.cybersoft.oshaneat.utils.JwtUtilHelper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
//            @Qualifier("tenbean")
    LoginServiceImp loginServiceImp;

    @Autowired
    JwtUtilHelper jwtUtilHelper;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam String username, @RequestParam String password) {
        ResponseData responseData = new ResponseData();

//        SecretKey key = Jwts.SIG.HS256.key().build(); //or HS384.key() or HS512.key()
//        String endcrypted = Encoders.BASE64.encode(key.getEncoded());
//        System.out.println(endcrypted);

        if (loginServiceImp.checkLogin(username, password)) {
           String token =  jwtUtilHelper.generateToken(username);
            responseData.setData(token);
        } else {
            responseData.setData("");
            responseData.setSuccess(false);
        }
        // cos the dung @JsonIgnore de tranh loi vong lap vo tan
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }


    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest) {
        ResponseData responseData = new ResponseData();
        responseData.setData(loginServiceImp.addUser(signUpRequest));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<?> getAllUser() {
        ResponseData responseData = new ResponseData();
        responseData.setData(loginServiceImp.getAllUser());
        // cos the dung @JsonIgnore de tranh loi vong lap vo tan
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
