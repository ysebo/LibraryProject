package com.example.project.service.auth.impl;

import com.example.project.config.JwtService;
import com.example.project.dto.auth.AuthLoginRequest;
import com.example.project.dto.auth.AuthLoginResponse;
import com.example.project.dto.user.UserRequest;
import com.example.project.entites.Customer;
import com.example.project.entites.User;
import com.example.project.enums.Role;
import com.example.project.exception.BadCredentialsException;
import com.example.project.repositories.UserRepository;
import com.example.project.service.auth.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.stereotype.Service;


import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final   JwtService jwtService;
    @Override
    public void register(UserRequest userRequest) {
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent())
            throw new BadCredentialsException("user with email: "+userRequest.getEmail()+" is already exist!");

        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setRole(userRequest.getRole());
        user.setPassword(encoder.encode(userRequest.getPassword()));
        if(userRequest.getRole().equals(Role.CUSTOMER)){
            Customer customer = new Customer();
            customer.setAge(userRequest.getAge());
            customer.setUser(user);
            customer.setName(
                    userRequest.getName()
            );
            user.setCustomer(customer);
            userRepository.save(user);
        }

        else {
            userRepository.save(user);
        }

    }

    @Override
    public AuthLoginResponse login(AuthLoginRequest authLoginRequest) {
        Optional<User> user = userRepository.findByEmail(authLoginRequest.getEmail());
        if (user.isEmpty())
            throw new BadCredentialsException("user not found");

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authLoginRequest.getEmail(),authLoginRequest.getPassword()));

        }catch (org.springframework.security.authentication.BadCredentialsException e){
            throw new BadCredentialsException("Never let problems to break you , dude. You have just forgotten your password that's all ))");
        }


        return convertToResponse(user);
    }
    private AuthLoginResponse convertToResponse(Optional<User> user) {
        AuthLoginResponse authLoginResponse = new AuthLoginResponse();
        authLoginResponse.setEmail(user.get().getEmail());
        authLoginResponse.setId(user.get().getId());
        if (user.get().getRole().equals(Role.CUSTOMER))
            authLoginResponse.setName(user.get().getCustomer().getName());
        Map<String, Object> extraClaims = new HashMap<>();

        String token = jwtService.generateToken(extraClaims, user.get());
        authLoginResponse.setToken(token);

        return authLoginResponse;
    }

    @Override
    public User getUsernameFromToken(String token) {
        String[] chunks = token.substring(7).split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        JSONParser jsonParser = new JSONParser();
        JSONObject object = null;
        try {
            object = (JSONObject) jsonParser.parse(decoder.decode(chunks[1]));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return userRepository.findByEmail(String.valueOf(object.get("sub"))).orElseThrow(() -> new RuntimeException("user can be null"));
    }

}
