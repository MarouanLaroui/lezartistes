package com.lezartistes.dao;

import com.lezartistes.models.User;

import java.io.Serializable;

/**
 *
 */
public abstract class UserDAO implements Serializable {

    public UserDAO() {
        super();
    }

    /**
     * Search a user in the database by his email and return it
     * @param mail the email of the searched user.
     * @return the use found
     */
    public abstract User getUserByMail(String mail);
}
