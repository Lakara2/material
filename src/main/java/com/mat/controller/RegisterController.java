package com.mat.controller;

import com.mat.model.User;
import com.mat.model.utils.UserRole;
import com.mat.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password, @RequestParam String role, RedirectAttributes redirectAttributes) {
        if (role.equals("ADMIN") || role.equals("USER")) {
            if (!isAdmin()) {
                redirectAttributes.addFlashAttribute("error", "Vous n'êtes pas autorisé à créer un utilisateur avec ce rôle.");
                return "redirect:/register";
            }
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRoles(UserRole.valueOf(role));

        userService.addUser(newUser);

        return "redirect:/login";
    }

    private boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

}
