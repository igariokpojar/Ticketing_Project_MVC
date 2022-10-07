package com.cydeo.entity;

import com.cydeo.enums.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor

// Why User is extended from the BaseEntity? : bc when ever I create a new Entity all field weill extend from BaseEntity why? because =>
// =>in my table I want to have User table including the BaseEntity fields, and when I will create projects I want to use this fields also.
public class User extends BaseEntity{

    private String firstName;
    private String lastName;
    private String userName;
    private String passWord;
    private boolean enabled; // when we create a new user we need a confirmation mechanism operation.
    private String phone;
    private Role role;
    private Gender gender;

    // I create manually constructor bc Lombok does not create initialization for the parent class(BaseEntity) fields
    public User(Long id, LocalDateTime insertDateTime, Long insertUserId, LocalDateTime lastUpdateDateTime, Long lastUpdateUserId, String firstName, String lastName, String userName, String passWord, boolean enabled, String phone, Role role, Gender gender) {
        super(id, insertDateTime, insertUserId, lastUpdateDateTime, lastUpdateUserId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.passWord = passWord;
        this.enabled = enabled;
        this.phone = phone;
        this.role = role;
        this.gender = gender;
    }
}
