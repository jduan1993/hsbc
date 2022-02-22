package com.hsbc.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String password;

    private Set<String> role;

    @Override
    public boolean equals(Object obj) {

        if(obj instanceof User) {

            return name.equals(((User) obj).name);
        }
        return false;
    }
}
