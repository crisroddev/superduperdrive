package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface FileMapper {
//  @Results(id = "superDuperFileResultMap", value = {
//          @Result(property = "fileId", column = "fileId"),
//          @Result(property = "name", column = "filename"),
//          @Result(property = "contentType", column = "contenttype"),
//          @Result(property = "size", column = "filesize"),
//          @Result(property = "userid", column = "userid"),
//          @Result(property = "data", column = "filedata")
//  })
  @Select("SELECT * FROM FILES WHERE userid = #{userid}")
  ArrayList<File> getFiles(User user);

//  @ResultMap("superDuperFileResultMap")
  @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
  File getFile(Integer fileId);

  @Select("SELECT COUNT(*) FROM FILES WHERE userid = #{userId} and filename = #{name}")
  int countFilesByFilename(File file);

  @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES (#{name}, #{contentType}, #{size}, #{userid}, #{data})")
  @Options(useGeneratedKeys = true, keyProperty = "fileId")
  int insert(File file);

  @Update("UPDATE FILES SET filename = #{name}, contenttype = #{contentType}, filesize = #{size}, filedata = #{data} where fileId = #{fileId}")
  int update(File file);

  @Delete("DELETE FILES WHERE fileId = #{fileId}")
  int deleteFile(Integer fileId);

  @Delete("DELETE FILES WHERE userid = #{userid}")
  int deleteFiles(User user);

  @Delete("DELETE FILES")
  int deleteAll();
}
