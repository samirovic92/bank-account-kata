package com.sg.bankaccountkata.infrasctructure.rest.dto;

import com.sg.bankaccountkata.domaine.Operation;
import com.sg.bankaccountkata.domaine.OperationType;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class OperationDto {

    private Double amount;
    private OperationType operationType;
    private OffsetDateTime date;

    public static List<OperationDto> from(List<Operation> operations) {
        return operations.stream()
                .map(OperationDto::from)
                .collect(Collectors.toList());
    }
    public static OperationDto from(Operation operation) {
        return OperationDto.builder()
                .amount(operation.getAmount())
                .operationType(operation.getOperationType())
                .date(operation.getDate())
                .build();
    }
}
