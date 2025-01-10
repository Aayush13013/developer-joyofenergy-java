package uk.tw.energy.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import uk.tw.energy.service.MeterReadingService;
import uk.tw.energy.service.UsageCostService;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class UsageCostControllerTest {
    @Mock
    private UsageCostService usageCostService;

    private UsageCostController usageCostController;

    @BeforeEach
    public void setUp() {
//        this.usageCostService = new UsageCostService();
        this.usageCostController = new UsageCostController(usageCostService);
    }



    @Test
    void givenSmartMeterIdShouldReturnSuccessfulCost() {
    }
}