package com.mss.src.iptt.src.processing;

import com.mss.src.iptt.src.model.Ticket;
import com.mss.src.iptt.src.model.TicketContainer;
import com.mss.src.iptt.src.model.ValueObject;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class JsonProcessor implements Processor {
    private static final String ORIGIN = "VVO";
    private static final String DESTINATION = "TLV";
    private static final int PERCENTILE = 90;

    @Override
    public void process(ValueObject data) {
        if (data instanceof TicketContainer) {
            TicketContainer container = (TicketContainer) data;

            List<Ticket> tickets = ((TicketContainer) data).getTickets()
                    .stream()
                    .filter((t) -> ORIGIN.equals(t.getOrigin()) && DESTINATION.equals(t.getDestination()))
                    .collect(Collectors.toList());


            System.out.println("Average flight time: " + durationToString(getAverageFlightTime(tickets)));
            System.out.println("90 percentile: " + durationToString(getPercentile(PERCENTILE, tickets)));
        }
    }

    private Duration getAverageFlightTime(List<Ticket> tickets) {
        long summaryFlightTime = 0;

        for (Ticket ticket : tickets) {
            summaryFlightTime += ticket.getFlightTime().getSeconds();
        }

        Duration averageFlightTime = Duration.ofSeconds(summaryFlightTime / tickets.size());

        return averageFlightTime;
    }

    private Duration getPercentile(int percentile, List<Ticket> tickets) {
        if (percentile < 0 || percentile > 100) {
            return null;
        }

        Ticket[] sortedTickets = tickets
                .stream()
                .sorted((l, r) -> (int) (l.getFlightTime().getSeconds() - r.getFlightTime().getSeconds()))
                .toArray(Ticket[]::new);

        int index = percentile / 100 * tickets.size();

        return sortedTickets[index].getFlightTime();
    }

    private String durationToString(Duration duration) {
        long daysCount = duration.toDays();
        long hoursCount = duration.toHours() - daysCount * 24;

        return daysCount +
                " day(s) " +
                hoursCount +
                " hour(s) " +
                (duration.toMinutes() - daysCount * 24 * 60 - hoursCount * 60) +
                " minute(s)";
    }
}
