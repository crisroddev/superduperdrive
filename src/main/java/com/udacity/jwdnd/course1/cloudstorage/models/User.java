package com.udacity.jwdnd.course1.cloudstorage.models;

public class User {
  private Integer userid;
  private String username;
  private String firstname;
  private String lastname;
  private String password;
  private String salt;

  public User(){}
  public User(String username, String firstname, String lastname, String password, String salt) {
    this.username = username;
    this.firstname = firstname;
    this.lastname = lastname;
    this.password = password;
    this.salt = salt;
  }

  public Integer getUserid() {
    return userid;
  }

  public void setUserid(Integer userid) {
    this.userid = userid;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }
}
