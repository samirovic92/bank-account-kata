package com.sg.bankaccountkata.infrasctructure.rest;

import com.sg.bankaccountkata.application.AccountStatementService;
import com.sg.bankaccountkata.application.OperationService;
import com.sg.bankaccountkata.domaine.Operation;
import com.sg.bankaccountkata.domaine.exception.AccountNotFoundException;
import com.sg.bankaccountkata.domaine.exception.UnauthorizedOperationException;
import com.sg.bankaccountkata.infrasctructure.rest.dto.AccountStatementDto;
import com.sg.bankaccountkata.infrasctructure.rest.dto.OperationDto;
import com.sg.bankaccountkata.infrasctructure.rest.exception.ApiError;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/account")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

    private OperationService operationService;
    private AccountStatementService accountStatementService;

    @PostMapping("/{accountNumber}/operations")
    @ResponseStatus(value= HttpStatus.CREATED)
    public void saveOperation(@RequestBody OperationDto operationDetail,
                                  @PathVariable Long accountNumber) throws AccountNotFoundException {
        this.operationService.saveOperation(toOperation(operationDetail), accountNumber);
    }

    @GetMapping("/{accountNumber}/account-statement")
    public AccountStatementDto operationsHistory(@PathVariable Long accountNumber) throws AccountNotFoundException {
        return AccountStatementDto.from(accountStatementService.getOperationsHistory(accountNumber));
    }

    private Operation toOperation(OperationDto operationDetail) {
        return Operation.builder()
                .amount(operationDetail.getAmount())
                .operationType(operationDetail.getOperationType())
                .date(OffsetDateTime.now())
                .build();
    }

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ApiError> handleAccountNotFoundException(final AccountNotFoundException ex) {

        return new ResponseEntity<>(
                new ApiError(HttpStatus.NOT_FOUND, ex.getMessage()), HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(UnauthorizedOperationException.class)
    @ResponseBody
    public ResponseEntity<ApiError> handleUnauthorizedOperationException(final UnauthorizedOperationException ex) {

        return new ResponseEntity<>(
                new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage()), HttpStatus.BAD_REQUEST
        );
    }
}
