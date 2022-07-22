package org.jpc.jspring.flutterwave.dtos;

import org.jpc.jspring.enums.CurrencyEnum;
import org.jpc.jspring.enums.NetworkEnum;

import lombok.Data;

@Data
public class MoMoPaymentPayload {
    private NetworkEnum network;
    private CurrencyEnum currency;
    private String paymentType;
    private String country;
    private String amount;
    private String emailAddress;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String IP;
    private String txRef;
    private String metaName;
    private String metaValue;
    private String redirectUrl;
    private String deviceFingerprint;

    public MoMoPaymentPayload(String paymentType, String country) {
        this.paymentType = paymentType;
        this.country = country;
    }
}