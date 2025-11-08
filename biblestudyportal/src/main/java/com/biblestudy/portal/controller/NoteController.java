package com.biblestudy.portal.controller;

import com.biblestudy.portal.model.Note;
import com.biblestudy.portal.model.User;
import com.biblestudy.portal.repository.NoteRepository;
import com.biblestudy.portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/notes")
    public String viewNotes(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        User user = userRepository.findByEmail(currentUser.getUsername());
        List<Note> notes = noteRepository.findByUser(user);
        model.addAttribute("notes", notes);
        model.addAttribute("note", new Note());
        return "notes";
    }

    @PostMapping("/notes/add")
    public String addNote(@ModelAttribute("note") Note note,
                          @AuthenticationPrincipal UserDetails currentUser) {
        User user = userRepository.findByEmail(currentUser.getUsername());
        note.setUser(user);
        noteRepository.save(note);
        return "redirect:/notes";
    }

    @GetMapping("/notes/delete/{id}")
    public String deleteNote(@PathVariable Long id) {
        noteRepository.deleteById(id);
        return "redirect:/notes";
    }
    @GetMapping("/notes/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid note ID: " + id));

        model.addAttribute("note", note);
        return "edit_note";
    }

    @PostMapping("/notes/update/{id}")
    public String updateNote(@PathVariable Long id, @ModelAttribute("note") Note updatedNote) {
        Note existingNote = noteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid note ID: " + id));

        existingNote.setTitle(updatedNote.getTitle());
        existingNote.setContent(updatedNote.getContent());

        noteRepository.save(existingNote);
        return "redirect:/notes";
    }

}
