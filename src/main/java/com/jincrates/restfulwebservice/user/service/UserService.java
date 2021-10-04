package com.jincrates.restfulwebservice.user.service;

import com.jincrates.restfulwebservice.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    private static List<User> users = new ArrayList<User>();

    private static int usersCount = 3;

    static {
        users.add(new User(1, "소크라테스", new Date()));
        users.add(new User(2, "플라톤", new Date()));
        users.add(new User(3, "아리스토텔레스", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }

        users.add(user);
        return user;
    }

    public User findOne(int id) {
        for (User user : users){
            if (user.getId() == id){
                return user;
            }
        }

        return null;
    }
}
