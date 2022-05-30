package com.mss.src.iptt.src.model;

import java.util.List;

public class TicketContainer extends ValueObject {
    private List<Ticket> tickets;

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
