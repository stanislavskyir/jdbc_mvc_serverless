package org.example;

import dao.TicketDao;
import entity.Ticket;

import java.math.BigDecimal;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        var ticketDao = TicketDao.getInstance();
        var ticket = new Ticket();
        ticket.setPassportNumber("A123A");
        ticket.setPassengerName("Ivan Ivanov");
        //ticket.setFlightId(6L);
        ticket.setSeatNumber("12B");
        ticket.setCost(BigDecimal.TEN);

        System.out.println(ticketDao.save(ticket));
    }
}
