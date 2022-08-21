package ru.gb.MyMarket.market.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.MyMarket.market.dto.statistics.ControllerStatisticDTO;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@RequiredArgsConstructor
public class StatisticService {
    private final List<ControllerStatisticDTO> list = new ArrayList<>();

    public List<ControllerStatisticDTO> getAll(){
        return list;
    }
}
