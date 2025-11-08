package com.biblestudy.portal.repository;

import com.biblestudy.portal.model.Note;
import com.biblestudy.portal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByUser(User user);
}
