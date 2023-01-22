package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface CredentialMapper {
//  @Results(id = "credentialResultMap", value = {
//          @Result(property = "credentialId", column = "credentialid"),
//          @Result(property = "url", column = "url"),
//          @Result(property = "username", column = "username"),
//          @Result(property = "key", column = "key"),
//          @Result(property = "password", column = "password"),
//          @Result(property = "userid", column = "userid")
//  })
  @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid}")
  ArrayList<Credential> getCredentials(User user);

//  @ResultMap("credentialResultMap")
  @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialId}")
  Credential getCredential(Integer credentialId);

  @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES (#{url}, #{username}, #{key}, #{password}, #{userid})")
  @Options(useGeneratedKeys = true, keyProperty = "credentialId")
  int insert(Credential credential);

  @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, key = #{key}, password = #{password} where credentialid = #{credentialId}")
  int update(Credential credential);

  @Delete("DELETE CREDENTIALS WHERE credentialId = #{credentialId}")
  int delete(Credential credential);

  @Delete("DELETE CREDENTIALS")
  int deleteAll();
}
