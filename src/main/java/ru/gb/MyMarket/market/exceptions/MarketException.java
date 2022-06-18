package ru.gb.MyMarket.market.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class MarketException {
    private String message;
    private Date date;

    public MarketException(String message) {
        this.message = message;
        this.date = new Date();
    }
}
