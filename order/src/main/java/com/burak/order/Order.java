package com.burak.order;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_order")
public class Order {

    @Id
    @SequenceGenerator(
            name = "order_id_sequence",
            sequenceName = "order_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_id_sequence"
    )
    private Integer id;

    @NonNull
    private Integer customerId;

    @NonNull
    private Integer bookId;

    @NonNull
    private Integer amount;

    @NonNull
    private Double purchased;

    @NonNull
    private LocalDateTime startDate;

    @NonNull
    private LocalDateTime endDate;
}
