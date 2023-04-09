package com.br.springtesteautomatizado.controllers;

import com.br.springtesteautomatizado.dtos.UserDto;
import com.br.springtesteautomatizado.exceptions.CpfAlreadyExistException;
import com.br.springtesteautomatizado.exceptions.CpfInvalidoException;
import com.br.springtesteautomatizado.exceptions.UserNotFoundException;
import com.br.springtesteautomatizado.interfaces.IUserService;
import com.br.springtesteautomatizado.models.User;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody UserDto userDto) throws CpfAlreadyExistException, CpfInvalidoException {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") Long id) throws UserNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Object> getAll(@PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable page) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll(page));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
    }
}
