package ru.gb.market.api.exception;


import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MarketException {//класс для описания ошибки
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

    public MarketException() {
    }
}
