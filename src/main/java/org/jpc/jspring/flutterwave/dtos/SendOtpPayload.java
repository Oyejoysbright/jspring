package org.jpc.jspring.flutterwave.dtos;

import lombok.Data;

@Data
public class SendOtpPayload {
    private int length, expiry;
    private CustomerData customer;
    private String sender;
    private Boolean send = true;
    private String[] medium;
}
