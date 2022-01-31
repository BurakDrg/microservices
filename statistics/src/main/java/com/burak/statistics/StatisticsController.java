package com.burak.statistics;

import com.burak.clients.order.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("statistics")
@AllArgsConstructor
@Slf4j
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping(path = "{customerId}")
    public ResponseEntity<List<Statistics>> getCustomerStatistics(@PathVariable("customerId") Integer customerId) {
        log.info("Get customer statistics");

        return new ResponseEntity<>(statisticsService.getStatistics(customerId), HttpStatus.OK);
    }
}
