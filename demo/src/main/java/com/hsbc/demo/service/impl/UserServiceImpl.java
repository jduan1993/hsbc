package com.hsbc.demo.service.impl;

import com.hsbc.demo.entity.Role;
import com.hsbc.demo.entity.User;
import com.hsbc.demo.service.UserService;
import com.hsbc.demo.util.Md5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.hsbc.demo.data.storage.*;

@Service
public class UserServiceImpl implements UserService {

    public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public int add(Map<String, String> map) {

        User user = new User();
        String userName = map.get("userName");
        String password = new Md5(map.get("password")).get();
        Set<String> role = new HashSet<>();
        user.setName(userName);
        user.setPassword(password);
        user.setRole(role);

        // Check duplicate for user
        if(USER_DATA.contains(user)) return 100;
        USER_DATA.add(user);
        logger.info("Current user list: {}", USER_DATA);
        return 0;
    }

    @Override
    public int delete(Map<String, String> map) {

        String userName = map.get("userName");

        // Check existence for user
        for(User user : USER_DATA) {
            if(user.getName().equals(userName)) {
                USER_DATA.remove(user);
                logger.info("Current user list: {}", USER_DATA);
                return 0;
            }
        }
        return 100;
    }

    @Override
    public int addRole(Map<String, String> map) {

        String userName = map.get("userName");
        String roleName = map.get("roleName");
        for(User user : USER_DATA) {
            if(user.getName().equals(userName)) {
                for(Role role : ROLE_DATA) {
                    if(role.getName().equals(roleName)) {

                        // User.role is HashSet, without duplicated word
                        user.getRole().add(role.getName());
                        logger.info("Current user list: {}", USER_DATA);
                        // return success, but nothing happened
                        return 0;
                    }
                }
                return 101;
            }
        }
        return 100;
    }

    @Override
    public String auth(Map<String, String> map) {

        String userName = map.get("userName");
        String password = new Md5(map.get("password")).get();
        for(User user : USER_DATA) {
            if(user.getName().equals(userName) && user.getPassword().equals(password)) {

                // Auth token generated by base64
                String token = Base64.getEncoder().encodeToString((userName + password).getBytes());

                // Pre-configured time 2h
                CURRENT_AUTH.put(token, Calendar.getInstance().getTimeInMillis() / 1000 + 7200);
                logger.info("Current token: {}", CURRENT_AUTH);
                return token;
            }
        }

        // Error if not found
        return "Username or password is wrong!";
    }

    @Override
    public void invalid(Map<String, String> map) {
        CURRENT_AUTH.remove(map.get("token"));
        logger.info("Current token: {}", CURRENT_AUTH);
    }
}