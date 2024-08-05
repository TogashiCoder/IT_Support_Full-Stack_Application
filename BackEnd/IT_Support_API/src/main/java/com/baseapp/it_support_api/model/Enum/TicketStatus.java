package com.baseapp.it_support_api.model.Enum;

public enum TicketStatus {
    CREATED,         // Ticket has been created
    ASSIGNED,        // Ticket has been assigned to a technician
    IN_PROGRESS,     // Work on the ticket is currently in progress
    COMPLETED,       // Work on the ticket has been completed
    CLOSED,          // Ticket has been closed
    CANCELLED        // Ticket has been cancelled
}
