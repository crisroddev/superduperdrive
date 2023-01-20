package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.Data;

@Data
public class Credential {
  private Integer credentialId;
  private String url;
  private String username;
  private String password;
  private Integer userid;
  private String key;

  public Credential() {
  }

  public Credential(Integer credentialId, String url, String username, String password, Integer userId, String key) {
    this.credentialId = credentialId;
    this.url = url;
    this.username = username;
    this.password = password;
    this.userid = userId;
    this.key = key;
  }
}
