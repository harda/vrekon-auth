package com.mpc.vauth.config;

import com.mpc.vauth.model.Roles;
import com.mpc.vauth.model.Users;
import com.mpc.vauth.repository.RolesRepository;
import com.mpc.vauth.repository.UsersRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitialUsers {
    Logger log = LogManager.getLogger(getClass());

    @Autowired
    UsersRepository usersRepository;
    @Autowired
    RolesRepository rolesRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void addInitialData(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        List<Roles> roles = new ArrayList<>();
        roles.add(new Roles(1, "Super Admin"));
        roles.add(new Roles(2,"Admin"));
        roles.add(new Roles(3,"User"));
        rolesRepository.saveAll(roles);

        List<Users> users = new ArrayList<>();
        users.add(new Users(1,1, "Super Admin", "sadmin", bCryptPasswordEncoder.encode("password"), "", ""));
        usersRepository.saveAll(users);

        log.info("Added roles and users");
    }
}
