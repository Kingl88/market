package ru.gb.MyMarket.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.MyMarket.market.dto.statistics.ControllerStatisticDTO;
import ru.gb.MyMarket.market.services.StatisticService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/api/v1/statistic")
@RequiredArgsConstructor
public class StatisticController {
    private final StatisticService statisticService;

    @MessageMapping("/giveMeFile")
    @SendTo("/topic/getFileAboutProducts")
    public String fromClient() throws InterruptedException {
        Thread.sleep(10000);
       return String.valueOf(statisticService.createFile());
    }

    @GetMapping("/")
    public List<ControllerStatisticDTO> getAll() {
        return statisticService.getAll();
    }

    @GetMapping(value = "/getFileAboutProducts", produces = "application/octet-stream")
    public ResponseEntity<byte []> getFile() throws IOException {
        HttpHeaders httpHeaders = new HttpHeaders();
        File file = statisticService.getExcelFile();
        byte[] result = Files.readAllBytes(Path.of(file.getAbsolutePath()));
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment().filename(file.getName()).build().toString());
        return ResponseEntity.ok().headers(httpHeaders).body(result);
    }
}
