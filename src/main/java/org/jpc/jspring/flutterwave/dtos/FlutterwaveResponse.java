package org.jpc.jspring.flutterwave.dtos;

import lombok.Data;

@Data
public class FlutterwaveResponse {
    private boolean hasError;
    private String status, message, request;
    private Object data;
}
