package ru.gb.market.auth.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.market.api.exception.MarketException;
import ru.gb.market.auth.dto.AuthRequest;
import ru.gb.market.auth.dto.AuthResponse;
import ru.gb.market.auth.services.UserService;
import ru.gb.market.auth.utils.jwtToken.JwtTokenUtil;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService; //имплиментируем UserService
    private final JwtTokenUtil jwtTokenUtil;//имплиментируем jwtTokenUtil
    private final AuthenticationManager authenticationManager;//имплиментируем authenticationManager

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) {//создаем токен
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));// проверяем аутификацию пользователя,если выполнилось без ошибок, значит пользователь аутифицирован
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>(new MarketException("Incorrect username or password"), HttpStatus.UNAUTHORIZED);//если пользователь не существует возвращаем сообщение об ошибке
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());//создаем объект UserDetails содержащий инфрмацию о пользователе
        String token = jwtTokenUtil.generateToken(userDetails);//генерируем токен
        return ResponseEntity.ok(new AuthResponse(token));//возвращаем токен, и на клиентской стороне подшиваем его в Header
    }
}
