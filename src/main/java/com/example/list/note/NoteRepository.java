package com.example.list.note;

import com.example.list.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    @Query("SELECT n FROM Note n WHERE (n.user = :user OR n.accessType = :accessType)")
    List<Note> findByUserAndAccessType(UserEntity user, AccessType accessType);
}
