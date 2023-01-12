package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CredentialService {
  private CredentialMapper credentialMapper;
  private EncryptionService encryptionService;

  public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
    this.credentialMapper = credentialMapper;
    this.encryptionService = encryptionService;
  }

  public boolean createCredential(Credential credential) {
    credential = encryptPassword(credential);
    return credentialMapper.insert(credential) == 1;
  }

  public boolean updateCredential(Credential credential) {
    credential = encryptPassword(credential);
    return credentialMapper.update(credential) == 1;
  }

  private Credential encryptPassword(Credential credential) {
    if (credential.getKey() == null) {
      credential.setKey(encryptionService.generateKey());
    }
    String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), credential.getKey());
    credential.setPassword(encryptedPassword);
    return credential;
  }

  public String decryptPassword(Credential credential) {
    return encryptionService.decryptValue(credential.getPassword(), credential.getKey());
  }

  public Credential getCredential(Integer credentialId) {
    return credentialMapper.getCredential(credentialId);
  }

  public ArrayList<Credential> getAllCredentials(User user) {
    return credentialMapper.getCredentials(user);
  }

  public boolean deleteCredential(Credential credential) {
    return credentialMapper.delete(credential) == 1;
  }

  public boolean deleteAll() {
    return credentialMapper.deleteAll() == 1 ;
  }

}
