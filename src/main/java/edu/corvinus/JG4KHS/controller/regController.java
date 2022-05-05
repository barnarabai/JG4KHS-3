package edu.corvinus.JG4KHS.controller;


import edu.corvinus.JG4KHS.User;
import edu.corvinus.JG4KHS.UserDummy;
import edu.corvinus.JG4KHS.repository.UserDummyRepository;
import edu.corvinus.JG4KHS.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Optional;


@Controller
public class regController {

    private final Logger logger = LoggerFactory.getLogger(regController.class);


    //Miután megvan a Repository
    @Autowired
    private UserDummyRepository userDummyRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    //1.
    @GetMapping("/")
    public String regController() {
        return "registration";
    }


    //Formhoz
    /*@PostMapping("/register")
    public String userRegistration(@ModelAttribute User user) {
        System.out.println(user.toString());System.out.println(user.toString());

        User user_inserted = userRepository.save(user); //Miután megvan a Repository

        return "index";
    }*/

    @PostMapping("/register")
    public String register(@Valid UserDummy userDummy, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            logger.info("Validation errors occurred!");
            model.addAttribute("szabaly","Validációs hiba!");
            return "registration";
        }

        Optional<User> foundUser = userRepository.findByUsername(userDummy.getUsername());
        if (!foundUser.isEmpty()) {
            model.addAttribute("foglalt","Ez a felhasználónév már foglalt!");
            return "registration";
        }

        System.out.println(userDummy.toString());
        //User user_inserted = userRepository.save(user); //Miután megvan a Repository
        User u = new User();
        u.setUsername(userDummy.getUsername());
        u.setName(userDummy.getName());
        u.setPassword(passwordEncoder.encode(userDummy.getPassword()));
        System.out.println(u.toString());
        User user_inserted = userRepository.save(u);

        return "index";
    }

    @PostMapping("/login")
    public String welcome(@RequestParam(required = false) String username, String password, Model model)
    {
     final Optional<User> foundUser = userRepository.findByUsername(username);

        if (foundUser.isEmpty()) {
            model.addAttribute("nincs","Nincs ilyen felhasználó!");
            return "index";
        }

        final User u = foundUser.get();

        if (!passwordEncoder.matches(password, u.getPassword()))
        {
            model.addAttribute("rossz","Rossz jelszó!");
            return "index";
        }

        return "welcome";

    }

    @PostMapping("/index")
    public String index() {return "index";}

    @PostMapping("/regi")
    public String regi() {return "registration";}


}
