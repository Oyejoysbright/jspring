package org.jpc.jspring.flutterwave;

import java.net.UnknownHostException;

import org.jpc.jspring.core.Response;
import org.jpc.jspring.flutterwave.dtos.MoMoPaymentPayload;
import org.jpc.jspring.utils.Helper;
import org.springframework.beans.BeanUtils;

import com.flutterwave.rave.java.entry.mobileMoney;
import com.flutterwave.rave.java.payload.mobilemoneyPayload;

public class PayWithMoMo {
    
    public Response pay(MoMoPaymentPayload payload) {
        mobilemoneyPayload flwPayload = new mobilemoneyPayload();
        
        BeanUtils.copyProperties(payload, flwPayload);

        flwPayload.setSecret_key(FlutterwavePayment.config.getSecKey());
        flwPayload.setPublic_key(FlutterwavePayment.config.getPubKey());
        flwPayload.setPBFPubKey(FlutterwavePayment.config.getPubKey());
        flwPayload.setEncryption_key(FlutterwavePayment.config.getEncKey());
        flwPayload.setCurrency(payload.getCurrency() != null? payload.getCurrency().toString() : FlutterwavePayment.config.getCurrency());
        flwPayload.setNetwork(payload.getNetwork().toString());
        flwPayload.setPayment_type(payload.getPaymentType());
        flwPayload.setPhonenumber(payload.getPhoneNumber());
        flwPayload.setFirstname(payload.getFirstName());
        flwPayload.setLastname(payload.getLastName());
        flwPayload.setEmail(payload.getEmailAddress());
        flwPayload.setMetaname(payload.getMetaName());
        flwPayload.setMetavalue(payload.getMetaValue());
        flwPayload.setRedirect_url(payload.getRedirectUrl());
        flwPayload.setDevice_fingerprint(payload.getDeviceFingerprint());

        mobileMoney payment = new mobileMoney();
        String rawResponse = null;
        Response res = new Response();
        try {
            rawResponse = payment.domobilemoney(flwPayload);
        } catch (UnknownHostException e) {
            res.setHasError(true);
            res.setMessage(e.getMessage());
            e.printStackTrace();
        }
        res = Helper.buildFlutterwaveResponse(rawResponse, true);
        return res;
    }
}
