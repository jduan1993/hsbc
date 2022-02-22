package com.hsbc.demo.controller;

import com.hsbc.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService service;

    @PostMapping("/create")
    public String add(@RequestBody Map<String, String> map) {

        if(map.isEmpty()) return "Required request body is empty!";
        if(map.get("roleName").isBlank()) return "Please input role name!";
        int response = service.add(map);
        if(response == 100) return "Role already exists!";
        return "New role created!";
    }

    @PostMapping("/delete")
    public String delete(@RequestBody Map<String, String> map) {

        if(map.isEmpty()) return "Required request body is empty!";
        if(map.get("roleName").isBlank()) return "Please input role name!";
        int response = service.delete(map);
        if(response == 100) return "Role doesn't exist!";
        return "Role deleted";
    }

    @PostMapping("/check")
    public boolean checkRole(@RequestBody Map<String, String> map) {

        if(map.isEmpty()) return false;
        return service.checkRole(map);
    }

    @PostMapping("/all")
    public Set<String> allRole(@RequestBody Map<String, String> map) {

        if(map.isEmpty()) return null;
        return service.allRole(map);
    }
}
