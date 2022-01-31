package com.burak.clients.book;

import java.util.Optional;

public record BookDTO(
        Integer id,
        String name,
        Integer amount,
        Double price) {

    Optional<Integer> getId() {
        return Optional.ofNullable(id());
    }
}
