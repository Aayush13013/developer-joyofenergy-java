package uk.tw.energy.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import uk.tw.energy.domain.ElectricityReading;
import uk.tw.energy.domain.PricePlan;
import uk.tw.energy.util.UsageCostUtil;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class UsageCostService {

    private final MeterReadingService meterReadingService;
    private final AccountService accountService;
    private final PricePlanService pricePlanService;

    public UsageCostService(MeterReadingService meterReadingService, AccountService accountService, PricePlanService pricePlanService) {
        this.meterReadingService = meterReadingService;
        this.accountService = accountService;
        this.pricePlanService = pricePlanService;
    }

    public BigDecimal calculateLastWeekUsageCost(String smartMeterId) throws Exception {
        Optional<List<ElectricityReading>> readings = meterReadingService.getReadings(smartMeterId);
        String pricePlanIdForSmartMeterId = accountService.getPricePlanIdForSmartMeterId(smartMeterId);

        if(pricePlanIdForSmartMeterId == null){
            throw new Exception("Error:no price plan found for smartMeterId");
        }

        PricePlan pricePlan = pricePlanService.getPricePlanFromId(pricePlanIdForSmartMeterId);
        List<ElectricityReading> lastWeekReadings = readings.get().stream()
                        .filter(e -> e.time().isAfter(Instant.now().minus(7, ChronoUnit.DAYS)))
                        .toList();

        return UsageCostUtil.calculateCost(lastWeekReadings, pricePlan);
    }
}
