package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.nio.file.Files;
import java.util.List;

@Mapper
public interface FileMapper {
    @Select("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES " +
            "(#{file.fileName}, #{file.contentType}, #{file.fileSize}, #{file.userId}, #{file.fileData})")
    Integer save(@Param("file")File file);

    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    List<File> findFilesByUserId(@Param("userId") Integer userId);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileId}")
    void deleteById(@Param("fileId") Integer fileId);

    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    File findById(@Param("fileId") Integer fileId);
}
