package org.graf.services;

import org.graf.model.CocktailUserDetails;
import org.graf.model.User;
import org.graf.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by User on 18.04.2017.
 */
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Transactional
    public User create(String userName, String passwordHash){
        return userRepository.save(new User(userName, passwordHash));
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = findByUserName(userName);
        if(user == null){
            new UsernameNotFoundException(String.format("User with userName=%s was not found", userName));
        }
        return new CocktailUserDetails(user);
    }
}
