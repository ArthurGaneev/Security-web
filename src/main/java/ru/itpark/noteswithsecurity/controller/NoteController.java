package ru.itpark.noteswithsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itpark.noteswithsecurity.entity.NoteEntity;
import ru.itpark.noteswithsecurity.service.NoteService;

@Controller
@RequestMapping("/notes")
public class NoteController {
    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("notes", service.findAll());

        return "pages/notes";
    }

    @PreAuthorize("hasAuthority('ADD')")
    @GetMapping("/add")
    public String addForm() {
        return "pages/note-add";
    }

    @PreAuthorize("hasAuthority('ADD')")
    @PostMapping("/add")
    public String add(@ModelAttribute NoteEntity note) {
        service.add(note);

        return "redirect:/notes";
    }

    @GetMapping("/{id}") // {id} -> /notes/1
    public String get(@PathVariable int id, Model model) {
        model.addAttribute("note", service.findById(id));

        return "pages/note";
    }

    @PreAuthorize("hasAuthority('REMOVE')")
    @PostMapping("/{id}/remove")
    public String remove(@PathVariable int id) {
        service.removeById(id);

        return "redirect:/notes";
    }

    @PreAuthorize("@accountService.isOwned(#id)") // #id -> @PathVariable int id
    @GetMapping("/{id}/owned")
    public String preAuthorizeWithOurService(@PathVariable int id) {
        return "pages/owned";
    }
}
