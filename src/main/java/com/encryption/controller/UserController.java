package com.encryption.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encryption.entity.EncryptedPayload;
import com.encryption.service.UserService;



@RestController
@RequestMapping("/users")
public class UserController {
	 
	    @Autowired
	    private UserService userService;

	    @PostMapping("/save")
	    public EncryptedPayload createUser(@RequestBody EncryptedPayload request) throws Exception {
	        return userService.saveUser(request);
	    }

	    @GetMapping("/getAll")
	    public EncryptedPayload getUsers() throws Exception {
	        return userService.getAllUsers();
	    }
	    
	    @GetMapping("/{encryptedId}")
	    public EncryptedPayload getUserById(@PathVariable String encryptedId) throws Exception {
	        return userService.getUserById(encryptedId);
	    }

	    @DeleteMapping("/{encryptedId}")
	    public void deleteUser(@PathVariable String encryptedId) throws Exception {
	        userService.deleteUserById(encryptedId);
	    }

}
