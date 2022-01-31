package com.burak.statistics;

import com.burak.clients.customer.CustomerClient;
import com.burak.clients.customer.CustomerDTO;
import com.burak.clients.order.OrderClient;
import com.burak.clients.order.OrderDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StatisticsService {

    private final CustomerClient customerClient;

    public List<Statistics> getStatistics(Integer customerId) {

        List<OrderDTO> orderList = customerClient.getCustomerOrders(customerId).getBody();

        Map<Month, List<OrderDTO>> objectsPerHour = orderList.stream()
                .collect(Collectors.groupingBy(OrderDTO::getMonth));

        List<Statistics> statisticsList = new ArrayList<>();

        objectsPerHour.forEach((month,v) -> statisticsList.add(
                new Statistics(
                        month,
                        v.size(),
                        v.stream().map(x -> x.book().amount()).collect(Collectors.summingInt(Integer::intValue)),
                        v.stream().map(x -> x.purchased()).collect(Collectors.summingDouble(Double::doubleValue))))
        );


        return statisticsList;
    }
}
