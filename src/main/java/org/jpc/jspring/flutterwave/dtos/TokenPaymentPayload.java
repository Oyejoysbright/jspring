package org.jpc.jspring.flutterwave.dtos;

import org.jpc.jspring.enums.CurrencyEnum;

import lombok.Data;

@Data
public class TokenPaymentPayload {
    private CurrencyEnum currency;
    private String country;
    private String amount;
    private String emailAddress;
    private String IP;
    private String firstName;
    private String lastName;
    private String token;
    private String txRef;
}
