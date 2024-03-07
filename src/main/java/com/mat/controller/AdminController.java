package com.mat.controller;

import com.mat.model.Material;
import com.mat.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private MaterialService materialService;

    @GetMapping("/materials")
    public String listMaterials(Model model) {
        List<Material> materials = materialService.getAllMaterials();
        model.addAttribute("materials", materials);
        return "admin/materials";
    }

    @GetMapping("/materials/{id}")
    public String viewMaterial(@PathVariable Long id, Model model) {
        Material material = materialService.getMaterialById(id);
        model.addAttribute("material", material);
        return "admin/view_material";
    }

    @GetMapping("/materials/add")
    public String showAddMaterialForm(Model model) {
        model.addAttribute("material", new Material());
        return "admin/add";
    }

    @PostMapping("/materials")
    public String addMaterial(@ModelAttribute("material") Material material) {
        materialService.addMaterial(material);
        return "redirect:/admin/materials";
    }

    @GetMapping("/materials/edit/{id}")
    public String showEditMaterialForm(@PathVariable Long id, Model model) {
        Material material = materialService.getMaterialById(id);
        model.addAttribute("material", material);
        return "admin/edit";
    }

    @PostMapping("/materials/edit/{id}")
    public String editMaterial(@PathVariable Long id, @ModelAttribute("material") Material material) {
        material.setId(id);
        materialService.updateMaterial(material);
        return "redirect:/admin/materials";
    }

    @GetMapping("/materials/delete/{id}")
    public String deleteMaterial(@PathVariable Long id) {
        materialService.deleteMaterial(id);
        return "redirect:/admin/materials";
    }
}
