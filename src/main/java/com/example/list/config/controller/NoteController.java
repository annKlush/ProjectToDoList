package com.example.list.config.controller;

import com.example.list.dto.LoginDto;
import com.example.list.dto.RegisterDto;
import com.example.list.note.AccessType;
import com.example.list.note.Note;
import com.example.list.note.NoteService;
import com.example.list.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

import static com.example.list.note.NoteFieldsValidation.getErrorMessage;
import static com.example.list.note.NoteFieldsValidation.isNoteFieldsValid;

@RequiredArgsConstructor
@Controller
public class NoteController {
    private final NoteService noteService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

//    @GetMapping("/")
//    @ResponseBody
//    public ModelAndView note(Model model, Authentication authentication) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("authentication", authentication);
//        modelAndView.setViewName("index");
//        return modelAndView;
//    }
@GetMapping("/")
public String note(Model model, Authentication authentication) {
    model.addAttribute("authentication", authentication);
    return "index";
}

//    @GetMapping("/note/list")
//    public ModelAndView getAllNotes() {
//        ModelAndView result = new ModelAndView("note/list");
//        UserEntity user = userRepository.findByUsername(noteService.author()).get();
//        List<Note> notes = noteService.getNotesByUserAndAccess(user.getId());
//        result.addObject("noteList", notes);
//        result.addObject("author", noteService.author());
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        result.addObject("authentication", authentication);
//        return result;
//    }

    @GetMapping("/note/list")
    public String getAllNotes(Model model) {
        //ModelAndView result = new ModelAndView("note/list");
        UserEntity user = userRepository.findByUsername(noteService.author()).get();

        List<Note> notes = noteService.getNotesByUserAndAccess(user.getId());
//        result.addObject("noteList", notes);
//        result.addObject("author", noteService.author());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        result.addObject("authentication", authentication);
        model.addAttribute("noteList", notes);
        model.addAttribute("author", noteService.author());
        model.addAttribute("authentication", authentication);
        return "note/list";

    }

//    public String note(Model model, Authentication authentication) {
//        model.addAttribute("authentication", authentication);
//        return "index";
//    }


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
    public String add(@ModelAttribute Note note, Authentication authentication) {
        if (isNoteFieldsValid(note)) {
            UserEntity user = userRepository.findByUsername(authentication.getName()).get();
            note.setUser(user.getId());
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

    private String getBaseUrl(HttpServletRequest request) {
        String requestUrl = request.getRequestURI().toString();
        String servletPath = request.getServletPath();
        return requestUrl.replace(servletPath, "");
    }

    @GetMapping("/note/share")
    public ModelAndView share(Long id) {
        Note note = noteService.getById(id);
        if (note.getAccessType() == AccessType.PUBLIC) {
            ModelAndView sh = new ModelAndView("note/share");
            sh.addObject("note", note);
            return sh;
        }
        else {
            ModelAndView er = new ModelAndView("note/not-found");
            return er;
        }
    }
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "auth/register";
    }
    @GetMapping("/login")
    public String showLoginForm() {
        return "auth/login";
    }
    @PostMapping("/register")
    public RedirectView processRegistrationForm(@ModelAttribute RegisterDto registerDto) {
        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));
        Role role = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(role));
        userRepository.save(user);

        return new RedirectView("/login");
    }


    @PostMapping("/login")
    public RedirectView processLogin(@ModelAttribute LoginDto loginDto) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/note/list");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return redirectView;
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/auth/login";
    }

}
