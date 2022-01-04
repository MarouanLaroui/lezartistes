package com.lezartistes.controllers.user;

import com.lezartistes.models.User;

public class UserInformation {
    private static User user = null;

    private UserInformation() {}

    public static void setUser(User u) {
        if (user == null)
            user = u;
    }

    public static User getUser() {
        return user;
    }
}
