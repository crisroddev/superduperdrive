package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NoteService {
  private NoteMapper noteMapper;

  public NoteService(NoteMapper noteMapper) {
    this.noteMapper = noteMapper;
  }

  public boolean createNote(Note note) {
    return noteMapper.insert(note) == 1;
  }

  public boolean updateNote(Note note) {
    return noteMapper.update(note) == 1;
  }

  public Note getNote(Integer noteId) {
    return noteMapper.getNote(noteId);
  }

  public ArrayList<Note> getNotes(User user) {
    return noteMapper.getNotes(user);
  }

  public boolean deleteNote(Note note) {
    return noteMapper.delete(note) == 1;
  }

  public boolean deleteNotes(User user) {
    return noteMapper.deleteNotes(user) == 1;
  }
}
