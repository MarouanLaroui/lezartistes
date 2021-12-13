package com.lezartistes.dao;

import com.lezartistes.exceptions.UserNotFoundException;
import com.lezartistes.facades.UserFacade;
import com.lezartistes.models.User;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserFacadeTest {
    @Test
    void login() {
        UserFacade userfacade = new UserFacade();
        assertThrows(UserNotFoundException.class, () -> {
            userfacade.login("admin", "admin");
        });
    }

    @Test
    void compareCredentials_test_admin_admin() {
        UserFacade userfacade = new UserFacade();
        User u = new User("admin", "admin");
        String username = "admin";
        String password = "admin";

        assertTrue(userfacade.compareCredentials(username, password, u));
    }
}
