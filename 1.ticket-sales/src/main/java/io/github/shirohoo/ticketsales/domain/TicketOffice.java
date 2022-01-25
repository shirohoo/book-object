package io.github.shirohoo.ticketsales.domain;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

public class TicketOffice {
    private final AtomicLong amount;
    private final Queue<Ticket> tickets = new LinkedBlockingQueue<>();

    private TicketOffice(long amount, Ticket... tickets) {
        this.amount = new AtomicLong(amount);
        this.tickets.addAll(Arrays.asList(tickets));
    }

    public static TicketOffice of(long amount, Ticket... tickets) {
        return new TicketOffice(amount, tickets);
    }

    public Ticket getTicket() {
        if (tickets.size() > 0) {
            return tickets.poll();
        }
        throw new IllegalStateException();
    }

    public void plusAmount(long amount) {
        long balance = this.amount.get();
        this.amount.set(balance + amount);
    }

    public long currentAmount(){
        return amount.get();
    }
}
