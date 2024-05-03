package com.gerencia.api.gerenciaapi.service;


import com.gerencia.api.gerenciaapi.entities.User;
import com.gerencia.api.gerenciaapi.exceptions.ResourceNotFoundException;
import com.gerencia.api.gerenciaapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    public void insert(User user){
        userRepository.save(user);
    }

    public void delete(Long id){
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id " + id);
        }
        userRepository.deleteById(id);
    }

    public void update(long id, User user){
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));

        existingUser.setNome(user.getNome());
        existingUser.setEmail(user.getEmail());
        existingUser.setTelefone(user.getTelefone());
        existingUser.setPassword(user.getPassword());

        userRepository.save(existingUser);
    }

    public boolean existsById(Long id){
        return userRepository.existsById(id);
    }



}
