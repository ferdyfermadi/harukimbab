package com.ferdyfermadi.harukimbab.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ferdyfermadi.harukimbab.model.constant.ConstantTable;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = ConstantTable.TRANSACTION_DETAIL)
public class TransactionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    @JsonBackReference
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Column(name = "menu_price", nullable = false, updatable = false)
    private Long menuPrice;

    @Column(name = "qty", nullable = false)
    private Integer qty;

    @Column(name = "total_price")
    private Long totalPrice;

}
