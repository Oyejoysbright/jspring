package org.jpc.jspring.flutterwave.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlutterwaveResponse {
    private boolean hasError;
    private String status, message, request;
    private Object data;
}
