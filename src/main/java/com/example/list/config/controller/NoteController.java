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
@RequestMapping("/note")
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

    @GetMapping("/list")
    public ModelAndView getAllNotes() {
        ModelAndView result = new ModelAndView("note/list");
        result.addObject("noteList", noteService.getAll());
        return result;
    }
    @PostMapping("/delete")
    public String deleteNote(@RequestParam("id") long id) {
        noteService.deleteById(id);
        return "redirect:/note/list";
    }

    @GetMapping("/edit")
    public ModelAndView showEditNotePage(/*@RequestParam */Long id/*, Model model*/) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("note/edit");
        modelAndView.addObject("note", noteService.getById(id));
        return modelAndView;
    }

    @GetMapping("/add")
    public String showSaveForm(Model model) {
        model.addAttribute("note", new Note());
        return "note/add";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute Note note, Model model) {
        model.addAttribute("notes", noteService.add(note));
        return "/note/add";
    }
}
