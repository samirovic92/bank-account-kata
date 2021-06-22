package com.sg.bankaccountkata.infrasctructure.persistence;

import com.sg.bankaccountkata.domaine.OperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "OPERATION")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OperationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "operation_amount")
    private Double amount;

    @Column(name = "operation_date")
    private OffsetDateTime date;

    @Enumerated(value = EnumType.STRING)
    private OperationType operationType;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;
}
