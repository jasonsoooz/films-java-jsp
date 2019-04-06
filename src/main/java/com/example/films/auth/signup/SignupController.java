package com.example.films.auth.signup;

import com.example.films.auth.login.UserDTO;
import com.example.films.port.provides.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
public class SignupController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        System.out.println("in signup get");
        model.addAttribute("signup", new SignupDTO());
        return "auth/signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("signup") @Valid SignupDTO signup, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("signup", signup);
            return "auth/signup";
        }

        if (!signup.getPassword().equals(signup.getConfirmPassword())) {
            model.addAttribute("error", "Password and confirm password does not match");
            return "auth/signup";
        }

        if (userService.isUserExist(signup.getEmail())) {
            model.addAttribute("error", "Email already exists");
            return "auth/signup";
        }

        userService.saveUser(UserDTO.of(signup));
        return "redirect:auth/login";
    }
}