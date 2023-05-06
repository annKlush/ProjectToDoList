package com.example.list.config.controller;

import com.example.list.note.Note;
import com.example.list.note.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.stream.IntStream;

@RequiredArgsConstructor
@Controller
public class NoteController {
    private final NoteService noteService;
    @GetMapping("/HP")
    @ResponseBody
    public ModelAndView note(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("HP");
        return modelAndView;
    }

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
        ModelAndView result = new ModelAndView("note/add");
        return result;
    }

    @PostMapping("/note/add")
    public String add(@ModelAttribute Note note) {
        noteService.add(note);
        return "redirect:/note/list";

    @GetMapping("/edit")
    public ModelAndView showEditNotePage(/*@RequestParam */Long id/*, Model model*/) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("note/edit");
        modelAndView.addObject("note", noteService.getById(id));
        return modelAndView;
    }

    @PostMapping("/edit")
    public RedirectView editNote(@ModelAttribute Note note) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit");
        noteService.update(note);
        return new RedirectView("/note/list");
    }

    }
}
