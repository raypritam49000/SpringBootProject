package com.motaharinia.caching.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.motaharinia.caching.business.UserService;
import com.motaharinia.caching.presentation.model.UserModel;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public UserModel create(@RequestBody @Validated  UserModel userModel) {
        return userService.create(userModel);
    }

    @GetMapping("/user/{id}")
    public UserModel findOne(@PathVariable Integer id) {
        return userService.findOne(id);
    }

    @GetMapping("/user")
    public List<UserModel> findAll() {
        return userService.findAll();
    }

    @PutMapping("/user")
    public UserModel update(@RequestBody @Validated  UserModel userModel) {
        return userService.update(userModel);
    }

    @DeleteMapping("/user/{id}")
    public UserModel delete(@PathVariable Integer id) {
        return userService.delete(id);
    }
}
