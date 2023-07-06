package com.webreact.reactweb.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
    @Autowired
    private RepositoryUser repositoryUser;

    @GetMapping("/page")
    public List<Users> users(Users user){
        return repositoryUser.findAll();
    }
    @PostMapping("/page/save")
    public void saveUser(@RequestBody Users user){
        repositoryUser.save(user);
    }

    @GetMapping("/page/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id){
        Users user = repositoryUser.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return ResponseEntity.ok(user);
    }

    @PutMapping("/page/{id}")
    public ResponseEntity<Users> Update(@PathVariable Long id, @RequestBody Users users)
    {
        Users user = repositoryUser.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        user.setName(users.getName());
        user.setEmail(users.getEmail());
        user.setPassword(users.getPassword());
        Users updatedUser = repositoryUser.save(user);
        return ResponseEntity.ok(updatedUser);

    }

}
