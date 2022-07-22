package org.jpc.jspring.flutterwave;

import java.net.UnknownHostException;

import org.jpc.jspring.core.Response;
import org.jpc.jspring.enums.FlutterEndPointEnum;
import org.jpc.jspring.flutterwave.dtos.CardPaymentPayload;
import org.jpc.jspring.flutterwave.dtos.TokenPaymentPayload;
import org.jpc.jspring.utils.Helper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpMethod;

import com.flutterwave.rave.java.entry.cardPayment;
import com.flutterwave.rave.java.entry.tokenCharge;
import com.flutterwave.rave.java.entry.transValidation;
import com.flutterwave.rave.java.entry.validateCardCharge;
import com.flutterwave.rave.java.payload.cardLoad;
import com.flutterwave.rave.java.payload.tokenChargePayload;
import com.flutterwave.rave.java.payload.transverifyPayload;
import com.flutterwave.rave.java.payload.validateCardPayload;

@SuppressWarnings({"rawTypes"})
public class PayWithCard {

    private String rawResponse = null;
    
    public Response pay(CardPaymentPayload payload) throws UnknownHostException {
        cardLoad flwPayload = new cardLoad();
        BeanUtils.copyProperties(payload, flwPayload);

        flwPayload.setSecret_key(FlutterwavePayment.config.getSecKey());
        flwPayload.setPublic_key(FlutterwavePayment.config.getPubKey());
        flwPayload.setEncryption_key(FlutterwavePayment.config.getEncKey());

        flwPayload.setCardno(payload.getCardNumber());
        flwPayload.setExpirymonth(payload.getExpiryMonth());
        flwPayload.setExpiryyear(payload.getExpiryYear());
        flwPayload.setCurrency(payload.getCurrency() != null? payload.getCurrency().toString() : FlutterwavePayment.config.getCurrency());
        flwPayload.setPhonenumber(payload.getPhoneNumber());
        flwPayload.setFirstname(payload.getFirstName());
        flwPayload.setLastname(payload.getLastName());
        flwPayload.setEmail(payload.getEmailAddress());
        flwPayload.setMetaname(payload.getMetaName());
        flwPayload.setMetavalue(payload.getMetaValue());
        flwPayload.setRedirect_url(payload.getRedirectUrl());
        flwPayload.setDevice_fingerprint(payload.getDeviceFingerprint());
        flwPayload.setSuggested_auth(payload.getSuggestedAuth());
        flwPayload.setBillingaddress(payload.getBillingAddress());
        flwPayload.setBillingcity(payload.getBillingCity());
        flwPayload.setBillingcountry(payload.getBillingCountry());
        flwPayload.setBillingstate(payload.getBillingState());
        flwPayload.setBillingzip(payload.getBillingZip());

        rawResponse = new cardPayment().doflwcardpayment(flwPayload);
        return Helper.buildFlutterwaveResponse(rawResponse, false);
    }

    public Response validate(String ref, String otp) {
        validateCardPayload flwPayload = new validateCardPayload();
        flwPayload.setOtp(otp);
        flwPayload.setPBFPubKey(FlutterwavePayment.config.getPubKey());
        flwPayload.setTransaction_reference(ref);

        rawResponse = new validateCardCharge().doflwcardvalidate(flwPayload);
        return Helper.buildFlutterwaveResponse(rawResponse, false);
    }

    public Response verifyTxId(int transactionId) {
        try {
            rawResponse = Helper.REST_CONNECTOR.exchange(
                FlutterEndPointEnum.VERIFICATION.forTxVerification(transactionId),
                FlutterwavePayment.config.getHttpEntity(), HttpMethod.GET).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Helper.buildFlutterwaveResponse(rawResponse, true);
    }

    public Response verifyTxRef(String txRef) {
        transverifyPayload flwPayload = new transverifyPayload();
        flwPayload.setSECKEY(FlutterwavePayment.config.getSecKey());
        flwPayload.setTxref(txRef);
        rawResponse = new transValidation().bvnvalidate(flwPayload);
        return Helper.buildFlutterwaveResponse(rawResponse, false);
    }

    public Response tokenizedPayment(TokenPaymentPayload payload) throws UnknownHostException {
        tokenChargePayload flwPayload = new tokenChargePayload();
        BeanUtils.copyProperties(payload, flwPayload);

        flwPayload.setSECKEY(FlutterwavePayment.config.getSecKey());

        flwPayload.setCurrency(payload.getCurrency() != null? payload.getCurrency().toString() : FlutterwavePayment.config.getCurrency());
        flwPayload.setFirstname(payload.getFirstName());
        flwPayload.setLastname(payload.getLastName());
        flwPayload.setEmail(payload.getEmailAddress());
        rawResponse = new tokenCharge().dotokenizedcharge(flwPayload);
        return Helper.buildFlutterwaveResponse(rawResponse, false);
    }
}
