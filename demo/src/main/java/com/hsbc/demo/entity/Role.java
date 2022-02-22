package com.hsbc.demo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    @Override
    public boolean equals(Object obj) {

        if(obj instanceof Role) {

            return name.equals(((Role) obj).name);
        }
        return false;
    }
}
