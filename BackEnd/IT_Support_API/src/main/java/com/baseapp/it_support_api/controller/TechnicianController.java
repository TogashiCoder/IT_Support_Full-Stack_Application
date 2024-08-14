package com.baseapp.it_support_api.controller;

import com.baseapp.it_support_api.model.DTO.TechnicianDTO;
import com.baseapp.it_support_api.service.TechnicianService;
import com.baseapp.it_support_api.service.TechnicianServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/technicians")
@RequiredArgsConstructor
public class TechnicianController {

    private final TechnicianServiceImpl technicianService;

    @GetMapping
    public ResponseEntity<List<TechnicianDTO>> getAllTechnicians() {
        List<TechnicianDTO> technicians = technicianService.getAllTechnician();
        return ResponseEntity.ok(technicians);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechnicianDTO> getTechnicianById(@PathVariable Long id) {
        TechnicianDTO technician = technicianService.getTechnicianById(id);
        return ResponseEntity.ok(technician);
    }

    @PostMapping("/admin")
    public ResponseEntity<TechnicianDTO> createTechnician(@RequestBody TechnicianDTO technicianDTO) {
        TechnicianDTO createdTechnician = technicianService.createTechnician(technicianDTO);
        return ResponseEntity.ok(createdTechnician);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TechnicianDTO> updateTechnician(@PathVariable Long id, @RequestBody TechnicianDTO technicianDTO) {
        TechnicianDTO updatedTechnician = technicianService.updateTechnician(id, technicianDTO);
        return ResponseEntity.ok(updatedTechnician);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnician(@PathVariable Long id) {
        technicianService.deleteTechnician(id);
        return ResponseEntity.noContent().build();
    }
}
