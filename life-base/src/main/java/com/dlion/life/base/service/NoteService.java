package com.dlion.life.base.service;

import com.dlion.life.base.dao.NoteMapper;
import com.dlion.life.base.entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/7/19
 */
@Service
public class NoteService {

    @Autowired
    private NoteMapper noteMapper;

    public void addNote(Note note) {
        noteMapper.insertNote(note);
    }

    public Note getById(Integer id){
        return noteMapper.selectById(id);
    }

    public List<Note> listByUserId(Integer userId){
        return noteMapper.listByUserId(userId);
    }

    public void updateNote(Note note){
        noteMapper.update(note);
    }

    public void deleteNote(Integer id){
        noteMapper.deleteNote(id);
    }

}
