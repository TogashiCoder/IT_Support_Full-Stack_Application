package com.baseapp.it_support_api.service;

import com.baseapp.it_support_api.exception.StatusNotFoundException;
import com.baseapp.it_support_api.exception.TechnicianNotFoundException;
import com.baseapp.it_support_api.exception.TicketNotFoundException;
import com.baseapp.it_support_api.exception.UserNotFoundException;
import com.baseapp.it_support_api.model.DTO.*;
import com.baseapp.it_support_api.model.Entity.Person;
import com.baseapp.it_support_api.model.Entity.Technician;
import com.baseapp.it_support_api.model.Entity.Ticket;
import com.baseapp.it_support_api.model.Entity.User;
import com.baseapp.it_support_api.model.Enum.TicketStatus;
import com.baseapp.it_support_api.model.mapper.*;
import com.baseapp.it_support_api.repository.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TechnicianRepository technicianRepository;
    private final TicketMapper ticketMapper;
    private final PersonRepository personRepository;


@Override
public TicketDTO createTicket(TicketDTO ticketDTO) {
    Ticket ticket = ticketMapper.toEntity(ticketDTO);
    Person user = personRepository.findById(ticketDTO.getUserId())
            .orElseThrow(() -> new UserNotFoundException("User not found"));
    ticket.setUser((User) user);
    ticket.setCreationDate(LocalDate.now());
    ticket.setStatus(TicketStatus.CREATED);
    Ticket savedTicket = ticketRepository.save(ticket);
    return ticketMapper.toDTO(savedTicket);
}


    @Override
    public TicketDTO getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket with id " + id + " not found"));
        return ticketMapper.toDTO(ticket);
    }

    @Override
    public List<TicketDTO> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return ticketMapper.toDTOList(tickets);
    }

    @Override
    public TicketDTO updateTicket(Long id, TicketDTO ticketDTO) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket with id " + id + " not found"));
        ticket.setCreationDate(ticketDTO.getCreationDate());
        ticket.setDescription(ticketDTO.getDescription());
        ticket.setStatus(ticketDTO.getStatus());
        ticket.setUser(ticket.getUser());
        ticket.setTechnician(ticket.getTechnician());
        ticket.setFault(ticket.getFault());
        ticket.setEquipment(ticket.getEquipment());
        Ticket updatedTicket = ticketRepository.save(ticket);
        return ticketMapper.toDTO(updatedTicket);
    }

    @Override
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }



    @Override
    public TicketDTO linkTicketWithTechnician(Long ticketId, Long technicianId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));
        Person technician = personRepository.findById(technicianId)
                .orElseThrow(() -> new TechnicianNotFoundException("Technician not found"));
        ticket.setTechnician((Technician) technician);
        ticket.setStatus(TicketStatus.ASSIGNED);
        Ticket updatedTicket = ticketRepository.save(ticket);
        return ticketMapper.toDTO(updatedTicket);
    }

    @Override
    public List<TicketDTO> getAllTecketByUserId(Long userId) {
        User user = (User) personRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        List<Ticket> tickets = ticketRepository.findByUser(user);
        return ticketMapper.toDTOList(tickets);
    }

    @Override
    public List<TicketDTO> getAllTicketsByTechnicianId(Long technicianId) {
        Technician technician = (Technician) personRepository.findById(technicianId)
                .orElseThrow(() -> new TechnicianNotFoundException("Technician not found with id: " + technicianId));
        List<Ticket> tickets = ticketRepository.findByTechnician(technician);
        return ticketMapper.toDTOList(tickets);
    }



    @Override
    public List<TicketDTO> filterByStatus(String status) {
        TicketStatus ticketStatus;
        try {
            ticketStatus = TicketStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new StatusNotFoundException("Invalid ticket status: " + status);
        }

        List<Ticket> tickets = ticketRepository.findByStatus(ticketStatus);
        return ticketMapper.toDTOList(tickets);
    }






        //extra
    private final UserRepository userRepository;
    private final FaultRepository faultRepository;
    private final EquipmentRepository equipmentRepository;
    private final UserMapper userMapper;
    private final FaultMapper faultMapper;
    private final EquipmentMapper equipmentMapper;
    private final TechnicianMapper technicianMapper;

    public TicketWithDetailsDTO getTicketWithDetailsById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        UserDTO user = userRepository.findById(ticket.getUser().getId())
                .map(userMapper::toDTO)
                .orElse(null);

        FaultDTO fault = faultRepository.findById(ticket.getFault().getId())
                .map(faultMapper::toDTO)
                .orElse(null);

        EquipmentDTO equipment = equipmentRepository.findById(ticket.getEquipment().getId())
                .map(equipmentMapper::toDTO)
                .orElse(null);



        TicketWithDetailsDTO ticketWithDetails = new TicketWithDetailsDTO();
        ticketWithDetails.setId(ticket.getId());
        ticketWithDetails.setCreationDate(ticket.getCreationDate());
        ticketWithDetails.setDescription(ticket.getDescription());
        ticketWithDetails.setStatus(ticket.getStatus());
        ticketWithDetails.setUser(user);
        ticketWithDetails.setFault(fault);
        ticketWithDetails.setEquipment(equipment);



        return ticketWithDetails;
    }


    //extra
    public List<TicketWithDetailsDTO> getAllTicketsByUserId(Long userId) {
        List<Ticket> tickets = ticketRepository.findByUserId(userId);

        return tickets.stream()
                .map(ticket -> {
                    UserDTO user = userRepository.findById(ticket.getUser().getId())
                            .map(userMapper::toDTO)
                            .orElse(null);

                    FaultDTO fault = faultRepository.findById(ticket.getFault().getId())
                            .map(faultMapper::toDTO)
                            .orElse(null);

                    EquipmentDTO equipment = equipmentRepository.findById(ticket.getEquipment().getId())
                            .map(equipmentMapper::toDTO)
                            .orElse(null);

                    TicketWithDetailsDTO ticketWithDetails = new TicketWithDetailsDTO();
                    ticketWithDetails.setId(ticket.getId());
                    ticketWithDetails.setCreationDate(ticket.getCreationDate());
                    ticketWithDetails.setDescription(ticket.getDescription());
                    ticketWithDetails.setStatus(ticket.getStatus());
                    ticketWithDetails.setUser(user);
                    ticketWithDetails.setFault(fault);
                    ticketWithDetails.setEquipment(equipment);

                    return ticketWithDetails;
                })
                .collect(Collectors.toList());
    }




    private final TicketWithDetailsMapper ticketWithDetailsMapper;

    public List<TicketWithDetailsDTO> getAllTicketsWithDetails() {
        List<Ticket> tickets = ticketRepository.findAll();

        return tickets.stream()
                .map(ticket -> {
                    TicketWithDetailsDTO ticketWithDetails = ticketWithDetailsMapper.toDTO(ticket);

                    UserDTO user = userRepository.findById(ticket.getUser().getId())
                            .map(userMapper::toDTO)
                            .orElse(null);

                    FaultDTO fault = Optional.ofNullable(ticket.getFault())
                            .map(f -> faultRepository.findById(f.getId())
                                    .map(faultMapper::toDTO)
                                    .orElse(null))
                            .orElse(null);

                    EquipmentDTO equipment = Optional.ofNullable(ticket.getEquipment())
                            .map(e -> equipmentRepository.findById(e.getId())
                                    .map(equipmentMapper::toDTO)
                                    .orElse(null))
                            .orElse(null);

                    TechnicianDTO technician = Optional.ofNullable(ticket.getTechnician())
                            .map(tech -> technicianRepository.findById(tech.getId())
                                    .map(technicianMapper::toDTO)
                                    .orElse(null))
                            .orElse(null);

                    ticketWithDetails.setUser(user);
                    ticketWithDetails.setFault(fault);
                    ticketWithDetails.setEquipment(equipment);
                    ticketWithDetails.setTechnician(technician);

                    return ticketWithDetails;
                })
                .collect(Collectors.toList());
    }



    @Override
    public List<TicketWithDetailsDTO> getAllTicketsDataByTechnicianId(Long technicianId) {
        Technician technician = (Technician) personRepository.findById(technicianId)
                .orElseThrow(() -> new TechnicianNotFoundException("Technician not found with id: " + technicianId));

        List<Ticket> tickets = ticketRepository.findByTechnician(technician);

        return tickets.stream()
                .map(ticket -> {
                    TicketWithDetailsDTO ticketWithDetails = ticketWithDetailsMapper.toDTO(ticket);

                    UserDTO user = userRepository.findById(ticket.getUser().getId())
                            .map(userMapper::toDTO)
                            .orElse(null);

                    FaultDTO fault = Optional.ofNullable(ticket.getFault())
                            .map(f -> faultRepository.findById(f.getId())
                                    .map(faultMapper::toDTO)
                                    .orElse(null))
                            .orElse(null);

                    EquipmentDTO equipment = Optional.ofNullable(ticket.getEquipment())
                            .map(e -> equipmentRepository.findById(e.getId())
                                    .map(equipmentMapper::toDTO)
                                    .orElse(null))
                            .orElse(null);

                    TechnicianDTO technicianDTO = Optional.ofNullable(ticket.getTechnician())
                            .map(tech -> technicianRepository.findById(tech.getId())
                                    .map(technicianMapper::toDTO)
                                    .orElse(null))
                            .orElse(null);

                    ticketWithDetails.setUser(user);
                    ticketWithDetails.setFault(fault);
                    ticketWithDetails.setEquipment(equipment);
                    ticketWithDetails.setTechnician(technicianDTO);

                    return ticketWithDetails;
                })
                .collect(Collectors.toList());
    }





    @Override
    @Transactional
    public TicketDTO updateTicketStatus(Long ticketId, String newStatus) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket with id " + ticketId + " not found"));

        TicketStatus ticketStatus;
        try {
            ticketStatus = TicketStatus.valueOf(newStatus.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new StatusNotFoundException("Invalid ticket status: " + newStatus);
        }

        ticket.setStatus(ticketStatus);
        Ticket updatedTicket = ticketRepository.save(ticket);

        return ticketMapper.toDTO(updatedTicket);
    }

}






