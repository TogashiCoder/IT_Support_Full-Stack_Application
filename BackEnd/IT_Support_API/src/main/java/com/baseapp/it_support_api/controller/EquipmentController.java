package com.baseapp.it_support_api.controller;

import com.baseapp.it_support_api.exception.EquipmentNotFoundException;
import com.baseapp.it_support_api.model.DTO.EquipmentDTO;
import com.baseapp.it_support_api.model.Entity.User;
import com.baseapp.it_support_api.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @GetMapping
    public ResponseEntity<List<EquipmentDTO>> getAllEquipment() {
        List<EquipmentDTO> equipmentList = equipmentService.getAllEquipment();
        return ResponseEntity.ok(equipmentList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentDTO> getEquipmentById(@PathVariable Long id) {
        try {
            EquipmentDTO equipmentDTO = equipmentService.getEquipmentById(id);
            return ResponseEntity.ok(equipmentDTO);
        } catch (EquipmentNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<EquipmentDTO> createEquipment(@RequestBody EquipmentDTO equipmentDTO) {
        EquipmentDTO createdEquipment = equipmentService.createEquipment(equipmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEquipment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipmentDTO> updateEquipment(@PathVariable Long id, @RequestBody EquipmentDTO equipmentDTO) {
        try {
            EquipmentDTO updatedEquipment = equipmentService.updateEquipment(id, equipmentDTO);
            return ResponseEntity.ok(updatedEquipment);
        } catch (EquipmentNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Long id) {
        try {
            equipmentService.deleteEquipment(id);
            return ResponseEntity.noContent().build();
        } catch (EquipmentNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
