package com.hsbc.demo.service;

import java.util.Map;

public interface UserService {

    int add(Map<String, String> map);

    int delete(Map<String, String> map);

    int addRole(Map<String, String> map);

    String auth(Map<String, String> map);

    void invalid(Map<String, String> map);
}
