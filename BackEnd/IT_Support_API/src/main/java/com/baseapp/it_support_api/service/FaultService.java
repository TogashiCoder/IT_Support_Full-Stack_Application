package com.baseapp.it_support_api.service;

import com.baseapp.it_support_api.model.DTO.FaultDTO;

import java.util.List;

public interface FaultService {
    List<FaultDTO> getAllFaults();
    FaultDTO getFaultById(Long id);
    FaultDTO createFault(FaultDTO faultDTO);
    FaultDTO updateFault(Long id, FaultDTO faultDTO);
    void deleteFault(Long id);
}
