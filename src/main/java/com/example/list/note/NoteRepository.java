package com.example.list.note;

import com.example.list.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {


    List<Note> findByUseridOrAccessType(Long userid, AccessType accessType);

}
