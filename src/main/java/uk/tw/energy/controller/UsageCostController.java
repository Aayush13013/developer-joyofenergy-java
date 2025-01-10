package uk.tw.energy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.tw.energy.service.UsageCostService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cost")
public class UsageCostController {

    private final UsageCostService usageCostService;

    public UsageCostController(UsageCostService usageCostService) {
        this.usageCostService = usageCostService;
    }
    @GetMapping("/{smartMeterId}/lastWeek")
    public ResponseEntity<?> lastWeekUsageCost(@PathVariable String smartMeterId){
        try{
            BigDecimal cost = usageCostService.calculateLastWeekUsageCost(smartMeterId);
            return ResponseEntity.ok(cost);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error: while retrieving the cost");
        }
    }
}
