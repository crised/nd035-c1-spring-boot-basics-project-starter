package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    private CredentialMapper credentialMapper;
    private EncryptionService encryptionService;
    private UserService userService;

    private boolean success;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService, UserService userService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
        this.userService = userService;
    }

    public List<Credential> getCredentials(String username) {
        User user = userService.getUser(username);
        if (user == null) return null;
        List<Credential> credentialList = credentialMapper.getCredentialsFromUser(user.getUserid());
        for (Credential credential : credentialList) {
            credential.setPlainpassword(encryptionService.decryptValue(credential.getPassword(), credential.getKey()));
        }
        return credentialList;
    }

    public boolean addCredential(Credential credential, String username) {
        success = false;
        User user = userService.getUser(username);
        if (user != null) {
            credential.setUserid(user.getUserid());
            credential.generateKey();
            credential.setPassword(encryptionService.encryptValue(credential.getPlainpassword(), credential.getKey()));
            credentialMapper.insert(credential);
            success = true;
            return true;
        }
        return false;
    }

    public boolean editCredential(Credential credential, String username) {
        success = false;
        User user = userService.getUser(username);
        String credentialKey = credentialMapper.getKeyByCredentialKey(credential.getCredentialid());
        if (user != null) {
            credential.setPassword(encryptionService.encryptValue(credential.getPlainpassword(), credentialKey));
            credentialMapper.update(credential);
            success = false;
        }
        return true;
    }

    public boolean removeCredential(Integer credentialId) {
        success = false;
        if (credentialMapper.deleteNote(credentialId) == 1) {
            success = true;
            return true;
        }
        return false;
    }

    public boolean isSuccess() {
        return success;
    }
}
