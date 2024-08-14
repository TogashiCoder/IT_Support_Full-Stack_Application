package com.baseapp.it_support_api.controller;

import com.baseapp.it_support_api.exception.StatusNotFoundException;
import com.baseapp.it_support_api.model.DTO.TicketDTO;
import com.baseapp.it_support_api.model.DTO.TicketWithDetailsDTO;
import com.baseapp.it_support_api.service.TicketService;
import com.baseapp.it_support_api.service.TicketServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@AllArgsConstructor
public class TicketController {

    private final TicketService ticketService;




    @PostMapping("/user")
    public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketDTO ticketDTO) {
    TicketDTO createdTicket = ticketService.createTicket(ticketDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdTicket);
    }



    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable Long id) {
        TicketDTO ticket = ticketService.getTicketById(id);
        return ResponseEntity.ok(ticket);
    }

    @GetMapping
    public ResponseEntity<List<TicketDTO>> getAllTickets() {
        List<TicketDTO> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDTO> updateTicket(@PathVariable Long id, @RequestBody TicketDTO ticketDTO) {
        TicketDTO updatedTicket = ticketService.updateTicket(id, ticketDTO);
        return ResponseEntity.ok(updatedTicket);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }



    @PutMapping("/admin/{id}/technician/{technicianId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<TicketDTO> linkTicketWithTechnician(@PathVariable Long id, @PathVariable Long technicianId) {
        TicketDTO linkedTicket = ticketService.linkTicketWithTechnician(id, technicianId);
        return ResponseEntity.ok(linkedTicket);
    }


//    @GetMapping("/users/{userId}")
//    public ResponseEntity<List<TicketDTO>> getAllTicketsByUserId(@PathVariable Long userId) {
//        List<TicketDTO> tickets = ticketService.getAllTecketByUserId(userId);
//        return ResponseEntity.ok(tickets);
//    }






    @GetMapping("/technicians/{technicianId}")
    public ResponseEntity<List<TicketDTO>> getAllTicketsByTechnicianId(@PathVariable Long technicianId) {
        List<TicketDTO> tickets = ticketService.getAllTicketsByTechnicianId(technicianId);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filterTicketsByStatus(@RequestParam String status) {
        try {
            List<TicketDTO> filteredTickets = ticketService.filterByStatus(status);
            return ResponseEntity.ok(filteredTickets);
        } catch (StatusNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error");
        }
    }



    //extra
    private final TicketServiceImpl ticketServiceIml;

    @GetMapping("/details/{id}")
    public ResponseEntity<TicketWithDetailsDTO> getTicketWithDetailsById(@PathVariable Long id) {
        try {
            TicketWithDetailsDTO ticketWithDetails = ticketServiceIml.getTicketWithDetailsById(id);
            return ResponseEntity.ok(ticketWithDetails);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }


    //extra
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TicketWithDetailsDTO>> GetAllTicketsByUserId(@PathVariable Long userId) {
        List<TicketWithDetailsDTO> tickets = ticketServiceIml.getAllTicketsByUserId(userId);
        return ResponseEntity.ok(tickets);
    }



    //extra
    @GetMapping("/with-details")
    public ResponseEntity<List<TicketWithDetailsDTO>> getAllTicketsWithDetails() {
        List<TicketWithDetailsDTO> tickets = ticketServiceIml.getAllTicketsWithDetails();
        return ResponseEntity.ok(tickets);
    }


    //extra
    @GetMapping("/technicians/{technicianId}/details")
    public ResponseEntity<List<TicketWithDetailsDTO>> getAllTicketsDataByTechnicianId(@PathVariable Long technicianId) {
        List<TicketWithDetailsDTO> tickets = ticketService.getAllTicketsDataByTechnicianId(technicianId);
        return ResponseEntity.ok(tickets);
    }


    @PutMapping("/{id}/status")
    public ResponseEntity<TicketDTO> updateTicketStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            TicketDTO updatedTicket = ticketService.updateTicketStatus(id, status);
            return ResponseEntity.ok(updatedTicket);
        } catch (StatusNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
