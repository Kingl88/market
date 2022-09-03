package ru.gb.market.core.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.core.utils.excel.ExcelUtil;
import ru.gb.market.core.dto.statistics.ControllerStatisticDTO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@RequiredArgsConstructor
public class StatisticService {
    private final List<ControllerStatisticDTO> list = new ArrayList<>();
    private final ExcelUtil excelUtil;
    private final ProductService productService;
    public List<ControllerStatisticDTO> getAll(){
        return list;
    }
    public File getExcelFile() throws IOException {
        return excelUtil.getFile();
    }
    public boolean createFile(){
       return excelUtil.creatFile(productService.findAll());
    }
}