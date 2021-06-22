package com.sg.bankaccountkata.infrasctructure.persistence;

import com.sg.bankaccountkata.domaine.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ACCOUNT")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private Long accountNumber;

    private Double balance;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private List<OperationEntity> operations;

}
