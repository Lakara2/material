package com.mat.controller;

import com.mat.model.Material;
import com.mat.model.User;
import com.mat.service.MaterialService;
import com.mat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private MaterialService materialService;
    private UserService userService;

    @GetMapping("/materials")
    public String listMaterials(Model model) {
        List<Material> materials = materialService.getAllMaterials();
        model.addAttribute("materials", materials);
        return "user/materials";
    }

    @GetMapping("/materials/{id}")
    public String viewMaterial(@PathVariable Long id, Model model) {
        Material material = materialService.getMaterialById(id);
        model.addAttribute("material", material);
        return "user/view_material";
    }
    @GetMapping("/profile")
    public String viewProfile(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("user", user);
        return "user/profile";
    }

}

