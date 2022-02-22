package com.hsbc.demo.controller;

import com.hsbc.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/create")
    public String add(@RequestBody Map<String, String> map) {

        if(map.isEmpty()) return "Required request body is empty!";
        if(map.get("userName").isBlank()) return "Please input username!";
        int response = service.add(map);
        if(response == 100) return "User already exists!";
        return "New user created!";
    }

    @PostMapping("/delete")
    public String delete(@RequestBody Map<String, String> map) {

        if(map.isEmpty()) return "Required request body is empty!";
        if(map.get("userName").isBlank()) return "Please input username!";
        int response = service.delete(map);
        if(response == 100) return "User doesn't exist!";
        return "User deleted";
    }

    @PostMapping("/add-role")
    public String addRole(@RequestBody Map<String, String> map) {

        if(map.isEmpty()) return "Required request body is empty!";
        if(map.get("userName").isBlank() || map.get("roleName").isBlank()) return "Names are required!";
        int response = service.addRole(map);
        if(response == 100) return "User doesn't exist!";
        if(response == 101) return "Role doesn't exist!";
        return "Add role to user success!";
    }

    @PostMapping("/auth")
    public String getToken(@RequestBody Map<String, String> map) {

        if(map.isEmpty()) return "Required request body is empty!";
        return service.auth(map);
    }

    @PostMapping("/invalid")
    public void invalidateToken(@RequestBody Map<String, String> map) {

        service.invalid(map);
    }
}