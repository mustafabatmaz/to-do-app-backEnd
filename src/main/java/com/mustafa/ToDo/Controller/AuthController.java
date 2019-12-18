package com.mustafa.ToDo.Controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.mustafa.ToDo.DTO.LoginRequestDTO;
import com.mustafa.ToDo.DTO.UserCreateRequestDTO;
import com.mustafa.ToDo.Entity.User;
import com.mustafa.ToDo.Service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.mustafa.ToDo.Security.SecurityConstants.*;
import static com.mustafa.ToDo.Security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String userLogin(HttpServletResponse response, @RequestBody LoginRequestDTO loginRequestDTO) {
        User user = userService.findUsername(loginRequestDTO.getUsername()).orElseThrow(() -> new RuntimeException("Wrong username"));
        BCrypt.Result result = BCrypt.verifyer().verify(loginRequestDTO.getPassword().toCharArray(), user.getPassword());
         if (user != null && result.verified){
             String token = Jwts.builder()
                     .setSubject(user.getUsername())
                     .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                     .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                     .compact();
             response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
             return "OK";
         }
//        return "NOT_OK";
        throw new RuntimeException("Giriş yapılmadı");

    }

    @RequestMapping("/register")
    public void userRegister(@RequestBody UserCreateRequestDTO userCreateRequestDTO){
            userService.addUser(userCreateRequestDTO);
    }
}
