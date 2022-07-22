package org.jpc.jspring.flutterwave.dtos;

import lombok.Data;

@Data
public class CardResponse {
    private String token, month, year, last4;
}
