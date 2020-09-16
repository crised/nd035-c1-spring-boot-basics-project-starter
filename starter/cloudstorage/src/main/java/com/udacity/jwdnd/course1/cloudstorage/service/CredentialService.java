package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    private CredentialMapper credentialMapper;
    private EncryptionService encryptionService;
    private UserService userService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService, UserService userService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
        this.userService = userService;
    }

    public List<Credential> getCredentials(String username) {
        User user = userService.getUser(username);
        if (user == null) return null;
        return credentialMapper.getCrdentialsFromUser(user.getUserid());
    }

    public boolean addCredential(Credential credential, String username) {
        User user = userService.getUser(username);
        if (user != null) {
            credential.setUserid(user.getUserid());
            credential.generateKey();
            credential.setPassword(encryptionService.encryptValue(credential.getPlainpassword(), credential.getKey()));
            credentialMapper.insert(credential);
            return true;
        }
        return false;
    }

}
