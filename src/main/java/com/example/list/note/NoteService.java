package com.example.list.note;

import com.example.list.user.UserEntity;
import com.example.list.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NoteService {
    @Autowired
    private final NoteRepository noteRepository;

    public List<Note> getAll() {
        return noteRepository.findAll();
    }

    public List<Note> getAllNotesByUserAndAccessType(UserEntity user, AccessType accessType) {
        return noteRepository.findByUserAndAccessType(user, accessType);
    }

    public Note add(Note note) {
        return noteRepository.save(note);
    }

    public void deleteById(long id) {
        noteRepository.deleteById(id);
    }

    public void update(Note note) {
        noteRepository.save(note);
    }


    public String author() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return authentication.getName();
    }

    public Note getById(long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Note with id " + id + " not found"));
    }

}