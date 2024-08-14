package com.baseapp.it_support_api.service;

import com.baseapp.it_support_api.model.DTO.TicketDTO;
import com.baseapp.it_support_api.model.DTO.TicketWithDetailsDTO;

import java.util.List;

public interface TicketService {
    TicketDTO createTicket(TicketDTO ticketDTO);
    TicketDTO getTicketById(Long id);
    List<TicketDTO> getAllTickets();
    TicketDTO updateTicket(Long id, TicketDTO ticketDTO);
    void deleteTicket(Long id);
    TicketDTO linkTicketWithTechnician(Long ticketId, Long technicianId);

    List<TicketDTO> getAllTecketByUserId(Long userId);
    List<TicketDTO> getAllTicketsByTechnicianId(Long technicianId);

    List<TicketWithDetailsDTO> getAllTicketsDataByTechnicianId(Long technicianId);

    List<TicketDTO> filterByStatus(String status);


    TicketDTO updateTicketStatus(Long ticketId, String newStatus);

}
