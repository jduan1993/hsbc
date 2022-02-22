package com.hsbc.demo.service;

import java.util.Map;
import java.util.Set;

public interface RoleService {
    int add(Map<String, String> map);

    int delete(Map<String, String> map);

    boolean checkRole(Map<String, String> map);

    Set<String> allRole(Map<String, String> map);
}
