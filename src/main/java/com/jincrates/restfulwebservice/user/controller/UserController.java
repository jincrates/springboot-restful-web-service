package com.jincrates.restfulwebservice.user.controller;

import com.jincrates.restfulwebservice.user.entity.User;
import com.jincrates.restfulwebservice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    //전체 사용자 조회
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    // GET /users/1 or /users/10 -> String로 전달되지만 매개변수의 타입을 int로 하면 자동 형변환 처리됨
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        return service.findOne(id);
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        User savedUser = service.save(user);
    }
}
