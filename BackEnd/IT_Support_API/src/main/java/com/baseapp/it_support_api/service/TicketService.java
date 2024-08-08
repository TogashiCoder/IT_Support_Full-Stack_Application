package com.baseapp.it_support_api.service;

import com.baseapp.it_support_api.model.DTO.TicketDTO;

import java.util.List;

public interface TicketService {
    TicketDTO createTicket(TicketDTO ticketDTO);
    TicketDTO getTicketById(Long id);
    List<TicketDTO> getAllTickets();
    TicketDTO updateTicket(Long id, TicketDTO ticketDTO);
    void deleteTicket(Long id);
    TicketDTO linkTicketWithTechnician(Long ticketId, Long technicianId);
}
