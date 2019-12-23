package com.mpc.vauth.repository;

import com.mpc.vauth.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RolesRepository extends CrudRepository<Roles, Integer> {
}
