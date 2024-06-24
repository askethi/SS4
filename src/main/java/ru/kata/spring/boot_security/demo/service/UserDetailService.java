package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository ud;

    @Autowired
    public UserDetailService(UserRepository ud) {
        this.ud = ud;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return ud.findByUsername(username);
    }
}
