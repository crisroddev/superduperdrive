package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface NoteMapper {
  @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
  ArrayList<Note> getNotes(User user);

//  @ResultMap("noteResultMap")
  @Select("SELECT * FROM NOTES WHERE noteid = #{noteId}")
  Note getNote(Integer noteId);

  @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userid})")
  @Options(useGeneratedKeys = true, keyProperty = "noteId")
  int insert(Note note);

  @Update("UPDATE NOTES set notetitle = #{noteTitle}, notedescription = #{noteDescription}, userid = #{userid} WHERE noteid = #{noteId}")
  int update(Note note);

  @Delete("DELETE NOTES WHERE userid = #{userid}")
  int deleteNotes(User user);

  @Delete("DELETE NOTES WHERE noteid = #{noteId}")
  int delete(Note note);

  @Delete("DELETE NOTES")
  int deleteAll();
}
