package com.burak.clients.order;

import com.burak.clients.book.BookDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public record OrderDTO(
        Integer customerId,
        BookDTO book,
        Double purchased,
        LocalDateTime startDate) {


        public Month getMonth(){
                return startDate().getMonth();
        }
}
