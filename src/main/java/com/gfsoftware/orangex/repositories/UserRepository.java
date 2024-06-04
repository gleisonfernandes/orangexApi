package com.gfsoftware.orangex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gfsoftware.orangex.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
