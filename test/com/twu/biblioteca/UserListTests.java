package com.twu.biblioteca;

import com.twu.biblioteca.collections.UserList;
import com.twu.biblioteca.items.Movie;
import com.twu.biblioteca.user.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class UserListTests {
    private List<User> users;
    private UserList userList;

    @Before
    public void setUp() {
        users = new ArrayList<User>();

        users.add(new User("John Doe", "123-4567", "1234567", "", "johndoe@gmail.com"));
        users.add(new User("Mary Doe", "890-1234", "8901234", "", "marydoe@gmail.com"));

        userList = new UserList(users);
    }

    @Test
    public void shouldReturnRelationWithUsersAndCheckedOutItems() {
        // given
        User johnDoe = userList.getUserList().get(0);
        johnDoe.checkOutItem(new Movie("An American in Paris", 1951,"Vincente Minnelli", 7.2));

        // when
        String relation = userList.getAllItemsAndUsersRelation();

        // then
        assertEquals("User 123-4567 | An American in Paris\n", relation);
    }

    @Test
    public void shouldReturnLoggedInUser() {
        // given
        User tomDoe = new User("Tom Doe", "567-8901", "5678901", "", "");
        userList.getUserList().add(tomDoe);
        userList.logInUser("567-8901", "5678901");

        // when
        User user = userList.getLoggedInUser();

        // then
        assertEquals(true, user.equals(tomDoe));
    }
}
