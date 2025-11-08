package com.biblestudy.portal.repository;

import com.biblestudy.portal.model.Verse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VerseRepository extends JpaRepository<Verse, Long> {

    @Query(value = "SELECT * FROM verses ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Verse findRandomVerse();
}
