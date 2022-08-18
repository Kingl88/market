package ru.gb.MyMarket.market.dto.statistics;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
@NoArgsConstructor
public class ControllerStatisticDTO {
    private String nameController;
    private List<MethodStatistic> methods = new ArrayList<>();
    private Long totalTime;
    public void addMethod(String methodName, Long time){
        if(methods.stream().anyMatch(c->c.getNameMethod().equals(methodName))){
            methods.stream().filter(item->item.getNameMethod().equals(methodName)).peek(c->c.addTime(time)).toList();
            totalTime+= time;
        }
        else{
            methods.add(new MethodStatistic(methodName, time));
            totalTime+=time;
        }

    }
    public ControllerStatisticDTO(String nameController, String methodName, Long time) {
        this.nameController = nameController;
        this.methods = new ArrayList<>();
        methods.add(new MethodStatistic(methodName, time));
        this.totalTime = time;
    }
}
