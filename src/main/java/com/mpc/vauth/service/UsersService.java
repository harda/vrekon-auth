package com.mpc.vauth.service;

import com.mpc.vauth.model.TransactionResponse;
import com.mpc.vauth.model.Users;
import com.mpc.vauth.repository.UsersRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;

    Logger log = LogManager.getLogger(getClass());

    public TransactionResponse addUser(Users users){
        log.info(users);
        TransactionResponse transactionResponse = new TransactionResponse();
        try{
            Users responseUsers = usersRepository.save(users);
            transactionResponse.setMessage("Success");
            transactionResponse.setStatus(200);
            transactionResponse.setError("");
            transactionResponse.setDataDetail(responseUsers);
        }catch (Exception e){
            transactionResponse.setMessage(e.getMessage());
            transactionResponse.setStatus(500);
            transactionResponse.setError("Internal Server Error");
        }

        return transactionResponse;
    }

    public TransactionResponse editUser(Users users){
        log.info(users);
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            Users responseUsers = usersRepository.save(users);
            transactionResponse.setMessage("Success");
            transactionResponse.setStatus(200);
            transactionResponse.setError("");
            transactionResponse.setDataDetail(responseUsers);
        }catch (Exception e){
            transactionResponse.setMessage(e.getMessage());
            transactionResponse.setStatus(500);
            transactionResponse.setError("Internal Server Error");
        }

        return transactionResponse;
    }

    public TransactionResponse deleteUser(Users users){
        log.info(users);
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            usersRepository.delete(users);
            transactionResponse.setMessage("Success");
            transactionResponse.setStatus(200);
            transactionResponse.setError("");
            transactionResponse.setDataDetail(users);
        }catch (Exception e){
            transactionResponse.setMessage(e.getMessage());
            transactionResponse.setStatus(500);
            transactionResponse.setError("Internal Server Error");
        }

        return transactionResponse;
    }

    public Users getUserByUsername(String username){
        return usersRepository.findByUsername(username);
    }
}
