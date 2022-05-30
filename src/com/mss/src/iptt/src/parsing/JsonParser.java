package com.mss.src.iptt.src.parsing;

import com.mss.src.iptt.src.model.Carrier;
import com.mss.src.iptt.src.model.Ticket;
import com.mss.src.iptt.src.model.TicketContainer;
import com.mss.src.iptt.src.model.ValueObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonParser implements Parser {
    private static final char BYTE_ORDER_MARK = '\ufeff';

    private final JSONParser parser = new JSONParser();
    private TicketContainer result;

    @Override
    public ValueObject getResult() {
        return result;
    }

    @Override
    public boolean tryParse(File file) {
        try (FileReader reader = new FileReader(file)) {
            JSONObject container;

            char[] buffer = new char[1];

            // Skip BOM symbol
            if (reader.read(buffer) > 0 && buffer[0] != BYTE_ORDER_MARK) {
                reader.close();

                container = (JSONObject) parser.parse(new FileReader(file));
            }
            else {
                container = (JSONObject) parser.parse(reader);
            }

            TicketContainer ticketContainer = new TicketContainer();
            JSONArray ticketsArray = (JSONArray) container.get("tickets");
            List<Ticket> tickets = new ArrayList<>(ticketsArray.size());

            Iterator<JSONObject> iterator = ticketsArray.iterator();

            while (iterator.hasNext()) {
                JSONObject ticketObject = iterator.next();

                Ticket ticket = new Ticket();
                ticket.setOrigin((String) ticketObject.get("origin"));
                ticket.setOriginName((String) ticketObject.get("origin_name"));
                ticket.setDestination((String) ticketObject.get("destination"));
                ticket.setDestinationName((String) ticketObject.get("destination_name"));
                ticket.setStops((long) ticketObject.get("stops"));
                ticket.setPrice(new BigDecimal((long) ticketObject.get("price")));

                ticket.setDepartureDate(
                        parseDateTime((String) ticketObject.get("departure_date"), (String) ticketObject.get("departure_time")));

                ticket.setArrivalDate(
                        parseDateTime((String) ticketObject.get("arrival_date"), (String) ticketObject.get("arrival_time")));

                ticket.setCarrier(new Carrier((String) ticketObject.get("carrier")));

                tickets.add(ticket);
            }

            ticketContainer.setTickets(tickets);

            result = ticketContainer;

        } catch (IOException | ParseException e) {
            return false;
        }

        return true;
    }

    private LocalDateTime parseDateTime(String date, String time) {
        return LocalDateTime.parse(String.format("%s %s", date, time), DateTimeFormatter.ofPattern("dd.MM.yy k:mm"));
    }
}
