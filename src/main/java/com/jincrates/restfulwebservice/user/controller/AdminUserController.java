package com.jincrates.restfulwebservice.user.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.jincrates.restfulwebservice.exception.UserNotFoundException;
import com.jincrates.restfulwebservice.user.entity.User;
import com.jincrates.restfulwebservice.user.entity.UserV2;
import com.jincrates.restfulwebservice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.beans.SimpleBeanInfo;
import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminUserController {
    private final UserService service;

    //전체 사용자 조회
    @GetMapping("/users")
    public MappingJacksonValue retrieveAllUsers() {
        List<User> users = service.findAll();

        Set<String> filterSet = new HashSet<String>();
        filterSet.add("id");
        filterSet.add("name");
        filterSet.add("joinDate");
        filterSet.add("password");

        return customFilter(filterSet, "UserInfo", users);
    }

    // 1. URI를 이용한 API Version 관리
    @GetMapping("/v1/users/{id}")
    public MappingJacksonValue retrieveUserV1(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        Set<String> filterSet = new HashSet<String>();
        filterSet.add("id");
        filterSet.add("name");
        filterSet.add("joinDate");
        filterSet.add("ssn");

        return customFilter(filterSet, "UserInfo", user);
    }

    @GetMapping("/v2/users/{id}")
    public MappingJacksonValue retrieveUserV2(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        // User -> UserV2
        UserV2 userV2 = new UserV2();
        BeanUtils.copyProperties(user, userV2); // id, name, joinDate, password, ssm
        userV2.setGrade("VIP");

        Set<String> filterSet = new HashSet<String>();
        filterSet.add("id");
        filterSet.add("name");
        filterSet.add("joinDate");
        filterSet.add("grade");

        return customFilter(filterSet, "UserInfoV2", userV2);
    }


    // 2. Parameter를 이용한 API Version 관리
    @GetMapping(value = "/users/{id}/", params = "version=1")
    public MappingJacksonValue retrieveUserParamV1(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        Set<String> filterSet = new HashSet<String>();
        filterSet.add("id");
        filterSet.add("name");
        filterSet.add("joinDate");
        filterSet.add("ssn");

        return customFilter(filterSet, "UserInfo", user);
    }

    @GetMapping(value = "/users/{id}/", params = "version=2")
    public MappingJacksonValue retrieveUserParamV2(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        // User -> UserV2
        UserV2 userV2 = new UserV2();
        BeanUtils.copyProperties(user, userV2); // id, name, joinDate, password, ssm
        userV2.setGrade("VIP");

        Set<String> filterSet = new HashSet<String>();
        filterSet.add("id");
        filterSet.add("name");
        filterSet.add("joinDate");
        filterSet.add("grade");

        return customFilter(filterSet, "UserInfoV2", userV2);
    }


    // 3. Header를 이용한 API Version 관리
    @GetMapping(value = "/users/{id}", headers = "X-API-VERSION=1")  //임의값 입력
    public MappingJacksonValue retrieveUserHeaderV1(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        Set<String> filterSet = new HashSet<String>();
        filterSet.add("id");
        filterSet.add("name");
        filterSet.add("joinDate");
        filterSet.add("ssn");

        return customFilter(filterSet, "UserInfo", user);
    }

    @GetMapping(value = "/users/{id}", headers = "X-API-VERSION=2")
    public MappingJacksonValue retrieveUserHeaderV2(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        // User -> UserV2
        UserV2 userV2 = new UserV2();
        BeanUtils.copyProperties(user, userV2); // id, name, joinDate, password, ssm
        userV2.setGrade("VIP");

        Set<String> filterSet = new HashSet<String>();
        filterSet.add("id");
        filterSet.add("name");
        filterSet.add("joinDate");
        filterSet.add("grade");

        return customFilter(filterSet, "UserInfoV2", userV2);
    }


    // 4. Mime-Type을 이용한 API Version 관리
    @GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv1+json")
    public MappingJacksonValue retrieveUserMimeV1(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        Set<String> filterSet = new HashSet<String>();
        filterSet.add("id");
        filterSet.add("name");
        filterSet.add("joinDate");
        filterSet.add("ssn");

        return customFilter(filterSet, "UserInfo", user);
    }

    @GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv2+json")
    public MappingJacksonValue retrieveUserMimeV2(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        // User -> UserV2
        UserV2 userV2 = new UserV2();
        BeanUtils.copyProperties(user, userV2); // id, name, joinDate, password, ssm
        userV2.setGrade("VIP");

        Set<String> filterSet = new HashSet<String>();
        filterSet.add("id");
        filterSet.add("name");
        filterSet.add("joinDate");
        filterSet.add("grade");

        return customFilter(filterSet, "UserInfoV2", userV2);
    }

    public MappingJacksonValue customFilter(Set<String> setting, String id, Object obj) {

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(setting);

        FilterProvider filters = new SimpleFilterProvider().addFilter(id, filter);

        MappingJacksonValue mapping = new MappingJacksonValue(obj);
        mapping.setFilters(filters);

        return mapping;
    }
}
