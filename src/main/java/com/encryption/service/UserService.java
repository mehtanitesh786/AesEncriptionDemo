package com.encryption.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encryption.entity.EncryptedPayload;
import com.encryption.entity.User;
import com.encryption.repository.UserRepository;
import com.encryption.util.AESUtil;
import com.fasterxml.jackson.databind.ObjectMapper;



@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

  
    
    private final ObjectMapper objectMapper = new ObjectMapper();

    private String writeInternal(Object data) throws Exception {
        return AESUtil.encrypt(objectMapper.writeValueAsString(data));
    }

    private <T> T readInternal(String encryptedData, Class<T> clazz) throws Exception {
        return objectMapper.readValue(AESUtil.decrypt(encryptedData), clazz);
    }
    

    public EncryptedPayload saveUser(EncryptedPayload request) throws Exception {
        String decryptedData = AESUtil.decrypt(request.getData());
        User user = objectMapper.readValue(decryptedData, User.class);
        User savedUser = userRepository.save(user);
        EncryptedPayload response = new EncryptedPayload();
        response.setData(writeInternal(savedUser));
        return response;
    }

    public EncryptedPayload getAllUsers() throws Exception {
        List<User> users = userRepository.findAll();
        EncryptedPayload response = new EncryptedPayload();
        response.setData(writeInternal(users));
        return response;
    }
    public EncryptedPayload getUserById(String encryptedId) throws Exception {
       Long id = readInternal(encryptedId, Long.class);
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
             EncryptedPayload response = new EncryptedPayload();
            response.setData(writeInternal(user.get()));
            return response;
        }
        return null;
    }

    public void deleteUserById(String encryptedId) throws Exception {
    	Long id = readInternal(encryptedId, Long.class);
        userRepository.deleteById(id);
    }
}
