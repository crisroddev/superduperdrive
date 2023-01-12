package com.udacity.jwdnd.course1.cloudstorage.model;
import lombok.Data;
@Data
public class User {
  private Integer userid;
  private String username;
  private String firstname;
  private String lastname;
  private String salt;
  private String password;

  public User(){}
  public User(Integer userid, String username, String firstname, String lastname, String salt, String password) {
    this.userid = userid;
    this.username = username;
    this.firstname = firstname;
    this.lastname = lastname;
    this.salt = salt;
    this.password = password;
  }
}
