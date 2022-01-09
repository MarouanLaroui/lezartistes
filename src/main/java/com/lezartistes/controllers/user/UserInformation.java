package com.lezartistes.controllers.user;

import com.lezartistes.models.Expert;
import com.lezartistes.models.User;

public class UserInformation {
    private static User user = null;
    private static boolean isSP;

    private UserInformation() {}

    public static void setUser(User u) {
        if (user == null) {
            user = u;
            isSP = u instanceof Expert;
        }
    }

    public static User getUser() {
        return user;
    }

    public static boolean isServiceProvider () {
        return isSP;
    }
}
