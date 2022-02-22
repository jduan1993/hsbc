package com.hsbc.demo.data;

import com.hsbc.demo.entity.Role;
import com.hsbc.demo.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class Storage {

    public static ArrayList<User> USER_DATA = new ArrayList<>();

    public static ArrayList<Role> ROLE_DATA = new ArrayList<>();

    public static HashMap<String, Long> CURRENT_AUTH = new HashMap<>();
}
