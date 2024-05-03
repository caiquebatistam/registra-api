package com.gerencia.api.gerenciaapi.repositories;

import com.gerencia.api.gerenciaapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> findAll();




}
