package com.biblestudy.portal.service;

import com.biblestudy.portal.model.Verse;
import com.biblestudy.portal.repository.VerseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerseService {

    @Autowired
    private VerseRepository verseRepository;

    public Verse getDailyVerse() {
        return verseRepository.findRandomVerse();
    }
}
