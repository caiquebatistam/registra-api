package com.gerencia.api.gerenciaapi.resource;


import com.gerencia.api.gerenciaapi.entities.User;
import com.gerencia.api.gerenciaapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/public/user")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping(value = "findById/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User user = userService.getById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping(value = "/insert")
    public ResponseEntity<User> insert(@RequestBody User user) {
        userService.insert(user);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!this.userService.existsById(id)) return ResponseEntity.notFound().build();

        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        if (!this.userService.existsById(id)) return ResponseEntity.notFound().build();

        this.userService.update(id, user);
        return ResponseEntity.ok().body(user);
    }



}
