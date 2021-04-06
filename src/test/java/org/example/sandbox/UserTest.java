package org.example.sandbox;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class UserTest {

    private static User user1;
    private static User user2;
    private static User user3;

    @BeforeClass
    public static void initializeUsers() {
        user1 = new User("John", 35, Gender.MALE);
        user2 = new User("Mary", 34, Gender.FEMALE);
        user3 = new User("Jack", 7, Gender.MALE);
    }

    @Test
    public void getAllUsersTest() {
        // expected
        // actual
        List<User> expected = User.getAllUsers();
        List<User> actual = List.of(user1, user2);

        Assert.assertEquals("Test if lists are equal", expected, actual);
    }

    @Test
    public void getAllUsersByGenderTest() {
        // QA - quality assurance
        List<User> expected = User.getAllUsers(Gender.MALE);
        List<User> actual = List.of(user1, user3);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void failTest() {
        boolean condition = false;
        if (!condition) {
            Assert.fail();
        }
    }

}
