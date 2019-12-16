package com.mpc.vauth.service;

import com.mpc.vauth.model.Users;
import com.mpc.vauth.repository.UsersRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    UsersRepository usersRepository;

    Logger log = LogManager.getLogger(getClass());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepository.findByUsername(username);
        if (users != null){
            return new User(users.getUsername(), users.getPassword(), new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("User not found with username: "+ username);
        }
    }
}
