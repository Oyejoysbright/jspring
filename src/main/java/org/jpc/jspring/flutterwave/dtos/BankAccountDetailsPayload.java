package org.jpc.jspring.flutterwave.dtos;

import lombok.Data;

@Data
public class BankAccountDetailsPayload {
    private String accountNumber, accountBankCode, type, country;
}
