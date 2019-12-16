package com.mpc.vauth.controller;

import com.mpc.vauth.model.TransactionResponse;
import com.mpc.vauth.model.Users;
import com.mpc.vauth.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersControler {
    @Autowired
    UsersService usersService;

    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public TransactionResponse addUser(@RequestBody Users users){
        return usersService.addUser(users);
    }

    @RequestMapping(value = "/edit-user", method = RequestMethod.POST)
    public  TransactionResponse editUser(@RequestBody Users users){
        return usersService.editUser(users);
    }

    @RequestMapping(value = "/delete-user", method = RequestMethod.POST)
    public  TransactionResponse deleteUser(@RequestBody Users users){
        return usersService.deleteUser(users);
    }
}
