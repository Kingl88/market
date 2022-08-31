package ru.gb.MyMarket.market.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class MarketException {
    private List<String> messages;
    private Date date;

    public MarketException(List<String> messages) {
        this.messages = messages;
        this.date = new Date();
    }

    public MarketException(String message) {
        this(List.of(message));
    }

    public MarketException(String... messages) {
        this(Arrays.asList(messages));
    }
}
