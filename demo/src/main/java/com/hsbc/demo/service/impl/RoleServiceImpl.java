package com.hsbc.demo.service.impl;

import com.hsbc.demo.entity.Role;
import com.hsbc.demo.entity.User;
import com.hsbc.demo.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.hsbc.demo.data.Storage.*;

@Service
public class RoleServiceImpl implements RoleService {

    public static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Override
    public int add(Map<String, String> map) {

        Role role = new Role();
        String roleName = map.get("roleName");
        role.setName(roleName);

        // Check duplicate for role
        if(ROLE_DATA.contains(role)) return 100;
        ROLE_DATA.add(role);
        logger.info("Current role list: {}", ROLE_DATA);
        return 0;
    }

    @Override
    public int delete(Map<String, String> map) {

        String roleName = map.get("roleName");

        // Check existence for role
        for(Role role : ROLE_DATA) {
            if(role.getName().equals(roleName)) {
                ROLE_DATA.remove(role);
                logger.info("Current role list: {}", ROLE_DATA);
                return 0;
            }
        }
        return 100;
    }

    @Override
    public boolean checkRole(Map<String, String> map) {

        String token = map.get("token");
        checkToken(token);

        String roleName = map.get("roleName");
        String str = new String(Base64.getDecoder().decode(token));

        // Get username string
        String userNameInToken = str.substring(0, str.length() - 32);
        for(User user : USER_DATA) {
            if(user.getName().equals(userNameInToken)) {
                return user.getRole().contains(roleName);
            }
        }
        return false;
    }

    @Override
    public Set<String> allRole(Map<String, String> map) {

        String token = map.get("token");
        checkToken(token);

        String str = new String(Base64.getDecoder().decode(token));
        // Get username string
        String userNameInToken = str.substring(0, str.length() - 32);
        for(User user : USER_DATA) {
            if(user.getName().equals(userNameInToken)) {
                return user.getRole();
            }
        }
        return null;
    }

    // Check existence and expire time of token
    private void checkToken(String token) {

        if(!CURRENT_AUTH.containsKey(token)) throw new Error("Token was not existed!");
        if(Calendar.getInstance().getTimeInMillis() / 1000 > CURRENT_AUTH.get(token)) {
            // Delete expired token
            CURRENT_AUTH.remove("token");
            throw new Error("Token was expired!");
        }
    }
}
