package org.graf.model;

import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collections;

import static java.util.Collections.*;

/**
 * Created by User on 18.04.2017.
 */
public class CocktailUserDetails extends org.springframework.security.core.userdetails.User {

    private User user;

    public CocktailUserDetails(User user) {
        super(user.getUserName(), user.getPasswordHash(), emptyList());
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }
}
