package com.jincrates.restfulwebservice.user.service;

import com.jincrates.restfulwebservice.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserService {
    private static List<User> users = new ArrayList<User>();

    private static int usersCount = 3;

    static {
        users.add(new User(1, "소크라테스", new Date(), "pass1", "940420-1111111"));
        users.add(new User(2, "플라톤", new Date(), "pass2", "940420-2222222"));
        users.add(new User(3, "아리스토텔레스", new Date(), "pass3", "940420-3333333"));
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

    public User deleteById(int id) {
        Iterator<User> iterator = users.iterator(); //열거형 데이터

        while (iterator.hasNext()) {
            User user = iterator.next();

            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }

        return null;
    }

    public User update(User modUser) {
        Iterator<User> iterator = users.iterator(); //열거형 데이터

        while (iterator.hasNext()) {
            User user = iterator.next();

            if (user.getId().equals(modUser.getId())) {
                user.setId(modUser.getId());
                user.setName(modUser.getName());
                user.setJoinDate(modUser.getJoinDate());

                return user;
            }
        }

        return null;
    }
}
