package com.baseapp.it_support_api.service;

import com.baseapp.it_support_api.exception.StatusNotFoundException;
import com.baseapp.it_support_api.exception.TechnicianNotFoundException;
import com.baseapp.it_support_api.exception.TicketNotFoundException;
import com.baseapp.it_support_api.exception.UserNotFoundException;
import com.baseapp.it_support_api.model.DTO.TicketDTO;
import com.baseapp.it_support_api.model.Entity.Person;
import com.baseapp.it_support_api.model.Entity.Technician;
import com.baseapp.it_support_api.model.Entity.Ticket;
import com.baseapp.it_support_api.model.Entity.User;
import com.baseapp.it_support_api.model.Enum.TicketStatus;
import com.baseapp.it_support_api.model.mapper.TicketMapper;
import com.baseapp.it_support_api.repository.PersonRepository;
import com.baseapp.it_support_api.repository.TechnicianRepository;
import com.baseapp.it_support_api.repository.TicketRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

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


}
