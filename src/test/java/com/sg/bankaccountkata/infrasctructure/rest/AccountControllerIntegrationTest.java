package com.sg.bankaccountkata.infrasctructure.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sg.bankaccountkata.domaine.OperationType;
import com.sg.bankaccountkata.infrasctructure.rest.dto.OperationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void add_new_deposit_operation() throws Exception {
        final Long accountNumber = 2500l;
        OperationDto operationDto = buildDefaultOperation(OperationType.DEPOSIT);
        String url = String.format("/account/%s/operations", accountNumber);

        this.mockMvc.perform(post(url)
                .content(asJsonString(operationDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @Sql(scripts = "/test/load-operation-data.sql")
    public void add_new_WITHDRAWAL_operation() throws Exception {
        final Long accountNumber = 2500l;
        OperationDto operationDto = buildDefaultOperation(OperationType.WITHDRAWAL);
        String url = String.format("/account/%s/operations", accountNumber);

        this.mockMvc.perform(post(url)
                .content(asJsonString(operationDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @Sql(scripts = "/test/load-operation-data.sql")
    public void should_retrieve_all_operation_attached_to_accountNumber() throws Exception {
        final Long accountNumber = 2500l;
        String url = String.format("/account/%s/account-statement", accountNumber);

        this.mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountNumber").value(accountNumber))
                .andExpect(jsonPath("$.operations", hasSize(2)));
    }

    @Test
    public void should_return_bad_request_when_executing_operation_for_an_unknown_account() throws Exception{
        final Long accountNumber = 8000l;
        OperationDto operationDto = buildDefaultOperation(OperationType.DEPOSIT);
        String url = String.format("/account/%s/operations", accountNumber);

        this.mockMvc.perform(post(url)
                .content(asJsonString(operationDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void should_return_forbidden_when_executing_invalid_operation() throws Exception{
        final Long accountNumber = 2500l;
        OperationDto operationDto = buildInvalidOperation();
        String url = String.format("/account/%s/operations", accountNumber);

        this.mockMvc.perform(post(url)
                .content(asJsonString(operationDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    private OperationDto buildDefaultOperation(OperationType operationType) {
        return OperationDto.builder()
                .operationType(operationType)
                .amount(100d)
                .build();
    }

    private OperationDto buildInvalidOperation() {
        return OperationDto.builder()
                .operationType(OperationType.WITHDRAWAL)
                .amount(800d)
                .build();
    }

    protected static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
