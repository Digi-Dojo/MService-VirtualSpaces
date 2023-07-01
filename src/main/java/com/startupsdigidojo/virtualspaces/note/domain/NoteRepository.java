package com.startupsdigidojo.virtualspaces.note.domain;

import com.startupsdigidojo.virtualspaces.note.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    Optional<Note> findById(Long id);
    List<Note> findByPlaceId(Long id);
}
