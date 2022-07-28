package org.jpc.jspring.flutterwave;

import org.jpc.jspring.core.Response;
import org.jpc.jspring.enums.FlutterEndPointEnum;
import org.jpc.jspring.flutterwave.dtos.BankAccountDetailsPayload;
import org.jpc.jspring.utils.Helper;
import org.springframework.http.HttpMethod;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BankAccountManager {
    private String rawResponse = null;

    public Response getBankDetails(BankAccountDetailsPayload payload) {
        try {
            rawResponse = Helper.REST_CONNECTOR.exchange(
                Helper.anyToString(payload),
                FlutterEndPointEnum.BANK_ACC_VERIFICATION.url,
                FlutterwavePayment.config.getHeaders(),
                HttpMethod.POST).getBody();
            return Helper.buildFlutterwaveResponse(rawResponse, true);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Response getBanks() {
        try {
            rawResponse = Helper.REST_CONNECTOR.exchange(
                FlutterEndPointEnum.BANKS.forGetBanks(FlutterwavePayment.config.getCountryCode()),
                FlutterwavePayment.config.getHttpEntity(),
                HttpMethod.GET).getBody();
            return Helper.buildFlutterwaveResponse(rawResponse, true);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
