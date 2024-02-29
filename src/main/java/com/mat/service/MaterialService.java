package com.mat.service;

import com.mat.model.Material;
import com.mat.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    private MaterialRepository materialRepository;

    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }
    public List<Material> getMaterialsByUsername(String username) {
        return materialRepository.findByUsername(username);
    }

    public Material getMaterialById(Long id) {
        return materialRepository.findById(id).orElse(null);
    }

    public void addMaterial(Material material) {
        materialRepository.save(material);
    }

    public void updateMaterial(Material material) {
        materialRepository.save(material);
    }

    public void deleteMaterial(Long id) {
        materialRepository.deleteById(id);
    }
}
