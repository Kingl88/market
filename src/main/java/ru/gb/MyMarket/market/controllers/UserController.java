package ru.gb.MyMarket.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.MyMarket.market.dto.ProductDto;
import ru.gb.MyMarket.market.exceptions.DataValidationException;
import ru.gb.MyMarket.market.exceptions.ResourceNotFoundException;
import ru.gb.MyMarket.market.models.Category;
import ru.gb.MyMarket.market.models.Product;
import ru.gb.MyMarket.market.models.Role;
import ru.gb.MyMarket.market.models.User;
import ru.gb.MyMarket.market.services.UserService;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody @Validated User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult
                    .getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList()));
        }
        if(userService.findByUsername(user.getUsername()).isPresent()){
            throw new DataValidationException("A user with this username exists");
        }
        return userService.save(user);
    }
}
