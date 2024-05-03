package com.gerencia.api.gerenciaapi;

import com.gerencia.api.gerenciaapi.entities.User;
import com.gerencia.api.gerenciaapi.service.UserService;
import com.gerencia.api.gerenciaapi.resource.UserResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserResourceTest {

    @InjectMocks
    UserResource userResource;

    @Mock
    UserService userService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllReturnsAllUsers() {
        User user1 = new User();
        User user2 = new User();
        when(userService.getAll()).thenReturn(Arrays.asList(user1, user2));

        ResponseEntity<List<User>> response = userResource.findAll();

        assertEquals(2, response.getBody().size());
        verify(userService, times(1)).getAll();
    }

    @Test
    public void findByIdReturnsUser() {
        User user = new User();
        when(userService.getById(1L)).thenReturn(user);

        ResponseEntity<User> response = userResource.findById(1L);

        assertEquals(user, response.getBody());
        verify(userService, times(1)).getById(1L);
    }

    @Test
    public void insertAddsNewUser() {
        User user = new User();
        doNothing().when(userService).insert(user);

        ResponseEntity<User> response = userResource.insert(user);

        assertEquals(user, response.getBody());
        verify(userService, times(1)).insert(user);
    }

    @Test
    public void deleteRemovesUser() {
        doNothing().when(userService).delete(1L);
        when(userService.existsById(1L)).thenReturn(true);

        ResponseEntity<Void> response = userResource.delete(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(userService, times(1)).delete(1L);
    }

    @Test
    public void updateModifiesExistingUser() {
        User user = new User();
        doNothing().when(userService).insert(user);
        when(userService.existsById(1L)).thenReturn(true);
        doNothing().when(userService).update(1L, user);

        ResponseEntity<User> response = userResource.update(1L, user);

        assertEquals(user, response.getBody());
        verify(userService, times(1)).update(1L, user);
    }
}