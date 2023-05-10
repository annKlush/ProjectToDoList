package com.example.list.config.controller;

import com.example.list.note.Note;
import com.example.list.note.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.stream.IntStream;

import static com.example.list.note.NoteFieldsValidation.getErrorMessage;
import static com.example.list.note.NoteFieldsValidation.isNoteFieldsValid;

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

    @GetMapping("/note/error")
    public ModelAndView errorPage(@RequestParam("errorMessage") String errorMessage) {
        ModelAndView result = new ModelAndView("note/error");
        result.addObject("errorMessage", errorMessage);
        return result;
    }

    @PostMapping("/note/delete")
    public String deleteNote(@RequestParam("id") long id) {
        noteService.deleteById(id);
        return "redirect:/note/list";
    }

    @PostMapping("/auth/SignUp")
    public String signUpM(Model model) {
       return "redirect:/auth/register";
    }

    @GetMapping("/note/add")
    public String add(Model model) {
        model.addAttribute("note", new Note());
        return "note/add";
    }

    @PostMapping("/note/add")
    public String add(@ModelAttribute Note note) {
        if (isNoteFieldsValid(note)) {
            noteService.add(note);
            return "redirect:/note/list";
        }
        else {
            return "redirect:/note/error?errorMessage=" + getErrorMessage(note);
        }
    }

    @GetMapping("/note/edit")
    public ModelAndView showEditNotePage(Long id) {
        ModelAndView result = new ModelAndView("note/edit");
        result.addObject("note", noteService.getById(id));
        return result;
    }

    @PostMapping("/note/edit")
    public RedirectView editNote(@ModelAttribute Note note, RedirectAttributes attributes) {
        if (isNoteFieldsValid(note)) {
            noteService.update(note);
            return new RedirectView("/note/list");
        }
        else {
            RedirectView redirectView = new RedirectView("/note/error");
            attributes.addAttribute("errorMessage", getErrorMessage(note));
            return redirectView;
        }
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
