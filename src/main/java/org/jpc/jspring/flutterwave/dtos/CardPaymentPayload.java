package org.jpc.jspring.flutterwave.dtos;

import org.jpc.jspring.enums.CurrencyEnum;

import lombok.Data;

@Data
public class CardPaymentPayload {
    
    private String cardNumber;
    private String cvv;
    private String expiryMonth;
    private String expiryYear;
    private CurrencyEnum currency;
    private String country;
    private String amount;
    private String emailAddress;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String IP;
    private String txRef;
    private String metaName = "saveCard";
    private String metaValue = "true";
    private String redirectUrl;
    private String deviceFingerprint;
    private String pin;
    private String suggestedAuth = "PIN";
    private String billingZip;
    private String billingCity;
    private String billingAddress;
    private String billingState;
    private String billingCountry;
    private String test;
}
