package org.example.sandbox;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class UserTest {

    @Test
    public void helloWorldTest() {
        // expected
        // actual
        User user1 = new User("John", 35, Gender.MALE);
        User user2 = new User("Mary", 34, Gender.FEMALE);
        User user3 = new User("Jack", 7, Gender.MALE);

        List<User> expected = User.getAllUsers();
        List<User> actual = List.of(user1, user2);

        Assert.assertEquals(expected, actual);
    }

}
