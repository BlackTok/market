package ru.gb.spring.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.spring.market.dto.JwtRequest;
import ru.gb.spring.market.dto.JwtResponse;
import ru.gb.spring.market.services.UserService;
import ru.gb.spring.market.utils.JwtTokenUtils;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
   private final UserService userService;
   private final JwtTokenUtils jwtTokenUtils;
   private final AuthenticationManager authenticationManager;

   @PostMapping("/auth")
    public ResponseEntity createAuthToken(@RequestBody JwtRequest authRequest) {
       try {
           authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
       } catch (BadCredentialsException e) {
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
       }
       UserDetails userDetails = userService.loadUserByUserUsername(authRequest.getUsername());
       String token = jwtTokenUtils.generateToken(userDetails);
       return ResponseEntity.ok(new JwtResponse(token));
   }
}
