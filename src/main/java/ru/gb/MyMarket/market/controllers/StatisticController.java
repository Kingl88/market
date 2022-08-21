package ru.gb.MyMarket.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.MyMarket.market.dto.statistics.ControllerStatisticDTO;
import ru.gb.MyMarket.market.services.StatisticService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class StatisticController {
    private final StatisticService statisticService;
    @GetMapping("/statistic")
    public List<ControllerStatisticDTO> getAll(){
        return statisticService.getAll();
    }
}
