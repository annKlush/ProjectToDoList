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
    @GetMapping("/")
    @ResponseBody
    public ModelAndView note(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
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
    }

//    @GetMapping("/note/edit")
//    public String showEditForm(@RequestParam("id") long id, Model model) {
//        Note note = noteService.getById(id);
//        model.addAttribute("note", note);
//        return "edit";
//    }

    @GetMapping("/note/edit")
    public ModelAndView showEditNotePage(Long id) {
        ModelAndView result = new ModelAndView("note/edit");
        result.addObject("note", noteService.getById(id));
        return result;
    }

    @PostMapping("/note/edit")
    public RedirectView editNote(@ModelAttribute Note note) {
        noteService.update(note);
        return new RedirectView("/note/list");
    }

    @GetMapping("/auth/login")
    public ModelAndView loginPage(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("auth/login");
        return modelAndView;
    }

    @GetMapping("/auth/register")
    public ModelAndView registerPage(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("auth/register");
        return modelAndView;
    }


}
