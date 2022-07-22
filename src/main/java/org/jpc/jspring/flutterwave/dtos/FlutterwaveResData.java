package org.jpc.jspring.flutterwave.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class FlutterwaveResData {
    private Object transactionId, txId, paymentId;
    private Object txRef, orderRef, flwRef, raveRef;
    private Object amount, chargedAmount, appFee;
    private Object redirectUrl, deviceFingerprint, ip;
    private Object currency, status;
    private CardResponse card = new CardResponse();
}
