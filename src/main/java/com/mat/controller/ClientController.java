package com.mat.controller;

import com.mat.model.Material;
import com.mat.service.MaterialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {

    private MaterialService materialService;
    @GetMapping("/dashboard")
    public String clientDashboard() {
        return "client/dashboard";
    }

    @GetMapping("/materials")
    public String viewClientMaterials(Model model, Principal principal) {
        String username = principal.getName();
        List<Material> materials = materialService.getMaterialsByUsername(username);
        model.addAttribute("materials", materials);
        return "client/materials";
    }

}