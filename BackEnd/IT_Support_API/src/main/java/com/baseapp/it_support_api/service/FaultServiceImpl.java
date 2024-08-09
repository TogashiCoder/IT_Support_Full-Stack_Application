package com.baseapp.it_support_api.service;

import com.baseapp.it_support_api.exception.FaultNotFoundException;
import com.baseapp.it_support_api.model.DTO.FaultDTO;
import com.baseapp.it_support_api.model.Entity.Fault;
import com.baseapp.it_support_api.model.mapper.FaultMapper;
import com.baseapp.it_support_api.repository.FaultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FaultServiceImpl {

    @Autowired
    private FaultRepository faultRepository;

    @Autowired
    private FaultMapper faultMapper;

    public List<FaultDTO> getAllFaults() {
        List<Fault> faultList = faultRepository.findAll();
        return faultMapper.toDTOList(faultList);
    }

    public FaultDTO getFaultById(Long id) {
        Optional<Fault> fault = faultRepository.findById(id);
        if (fault.isPresent()) {
            return faultMapper.toDTO(fault.get());
        } else {
            throw new FaultNotFoundException("Fault with id " + id + " not found");
        }
    }

    public FaultDTO createFault(FaultDTO faultDTO) {
        Fault fault = faultMapper.toEntity(faultDTO);
        fault.setSignalDate(LocalDate.now());
        Fault savedFault = faultRepository.save(fault);
        return faultMapper.toDTO(savedFault);
    }

    public FaultDTO updateFault(Long id, FaultDTO faultDTO) {
        Optional<Fault> optionalFault = faultRepository.findById(id);
        if (optionalFault.isPresent()) {
            Fault fault = optionalFault.get();
            fault.setDescription(faultDTO.getDescription());
            fault.setSignalDate(faultDTO.getSignalDate());
            Fault updatedFault = faultRepository.save(fault);
            return faultMapper.toDTO(updatedFault);
        } else {
            throw new FaultNotFoundException("Fault with id " + id + " not found");
        }
    }

    public void deleteFault(Long id) {
        Optional<Fault> optionalFault = faultRepository.findById(id);
        if (optionalFault.isPresent()) {
            faultRepository.delete(optionalFault.get());
        } else {
            throw new FaultNotFoundException("Fault with id " + id + " not found");
        }
    }
}
