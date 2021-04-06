package org.example.sandbox;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class User {

    private static int countId = 0;

    private int id;
    private String name;
    private int age;
    private Gender gender;

    private static List<User> allUsers;

    public User(String name, int age, Gender gender) {
        if (allUsers == null) {
            allUsers = new ArrayList<>();
        }

        this.name = name;
        this.age = age;
        this.gender = gender;

        this.id = ++countId;
        allUsers.add(this);
    }

    public static List<User> getAllUsers() {
        return allUsers;
    }

    public static List<User> getAllUsers(Gender gender) {
        return allUsers.stream()
                .filter(u -> u.gender == gender)
                .collect(Collectors.toList());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && name.equals(user.name) && gender == user.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, gender);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
