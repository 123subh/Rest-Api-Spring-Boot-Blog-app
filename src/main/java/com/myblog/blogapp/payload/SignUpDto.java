package com.myblog.blogapp.payload;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class SignUpDto {

    private long id;
    private String email;
    private String name;
    private String username;
    private String password;
}
