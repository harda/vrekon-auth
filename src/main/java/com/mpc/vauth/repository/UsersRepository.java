package com.mpc.vauth.repository;

import com.mpc.vauth.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Integer> {
    Users findByUsername(String username);
}
