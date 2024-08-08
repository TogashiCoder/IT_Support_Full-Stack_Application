package com.baseapp.it_support_api.controller;

import com.baseapp.it_support_api.exception.FaultNotFoundException;
import com.baseapp.it_support_api.model.DTO.FaultDTO;
import com.baseapp.it_support_api.service.FaultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faults")
public class FaultController {

    @Autowired
    private FaultServiceImpl faultService;

    @GetMapping
    public ResponseEntity<List<FaultDTO>> getAllFaults() {
        List<FaultDTO> faultList = faultService.getAllFaults();
        return ResponseEntity.ok(faultList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FaultDTO> getFaultById(@PathVariable Long id) {
        try {
            FaultDTO faultDTO = faultService.getFaultById(id);
            return ResponseEntity.ok(faultDTO);
        } catch (FaultNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<FaultDTO> createFault(@RequestBody FaultDTO faultDTO) {
        FaultDTO createdFault = faultService.createFault(faultDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFault);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FaultDTO> updateFault(@PathVariable Long id, @RequestBody FaultDTO faultDTO) {
        try {
            FaultDTO updatedFault = faultService.updateFault(id, faultDTO);
            return ResponseEntity.ok(updatedFault);
        } catch (FaultNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFault(@PathVariable Long id) {
        try {
            faultService.deleteFault(id);
            return ResponseEntity.noContent().build();
        } catch (FaultNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
