package org.jpc.jspring.flutterwave;

import org.jpc.jspring.core.Response;
import org.jpc.jspring.enums.FlutterEndPointEnum;
import org.jpc.jspring.flutterwave.dtos.SendOtpPayload;
import org.jpc.jspring.flutterwave.dtos.ValidateOtpPayload;
import org.jpc.jspring.utils.Helper;
import org.springframework.http.HttpMethod;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OtpManager {
    private String rawResponse = null;

    public Response sendOtp(SendOtpPayload payload) {
        try {
            rawResponse = Helper.REST_CONNECTOR.exchange(
                Helper.anyToString(payload),
                FlutterEndPointEnum.OTP.url,
                FlutterwavePayment.config.getHeaders(),
                HttpMethod.POST).getBody();
            return Helper.buildFlutterwaveResponse(rawResponse, true);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Response validateOtp(ValidateOtpPayload payload) {
        try {
            log.info(Helper.MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(payload));
            rawResponse = Helper.REST_CONNECTOR.exchange(
                Helper.anyToString(payload),
                FlutterEndPointEnum.OTP.forOtpValidation(payload.getRef()),
                FlutterwavePayment.config.getHeaders(),
                HttpMethod.POST).getBody();
            return Helper.buildFlutterwaveResponse(rawResponse, true);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
