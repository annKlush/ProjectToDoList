package com.example.list.note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

   // List<Note> findByUserIdAndAccesstype(Long userId, String accesstype);
}
