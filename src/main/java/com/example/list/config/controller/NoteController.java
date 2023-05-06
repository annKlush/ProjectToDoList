package com.example.list.config.controller;

import com.example.list.note.Note;
import com.example.list.note.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
public class NoteController {
    private final NoteService noteService;

    @GetMapping("/note/list")
    public ModelAndView getAllNotes() {
        ModelAndView result = new ModelAndView("note/list");
        result.addObject("noteList", noteService.getAll());
        return result;
    }
    @PostMapping("/note/delete")
    public String deleteNote(@RequestParam("id") long id) {
        noteService.deleteById(id);
        return "redirect:/note/list";
    }


    @GetMapping("/note/add")
    public ModelAndView add(Model model) {
//        model.addAttribute("note", new Note());
//        return "add";
        ModelAndView result = new ModelAndView("note/add");
      //  result.addObject("noteList", noteService.getAll());
        return result;
    }

    @PostMapping("/note/add")
    public String add(@ModelAttribute Note note) {
        noteService.add(note);
        return "redirect:/note/list";
    }
}
