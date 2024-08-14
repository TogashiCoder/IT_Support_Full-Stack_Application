package com.baseapp.it_support_api.repository;

import com.baseapp.it_support_api.model.Entity.Technician;
import com.baseapp.it_support_api.model.Entity.Ticket;
import com.baseapp.it_support_api.model.Entity.User;
import com.baseapp.it_support_api.model.Enum.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    List<Ticket> findByUser(User user);
    List<Ticket> findByTechnician(Technician technician);
    List<Ticket> findByStatus(TicketStatus status);
    List<Ticket> findByUserId(Long userId);

}

