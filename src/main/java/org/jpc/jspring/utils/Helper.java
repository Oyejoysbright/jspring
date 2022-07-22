package org.jpc.jspring.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jpc.jspring.core.Response;
import org.jpc.jspring.flutterwave.dtos.FlutterwaveResData;
import org.jpc.jspring.flutterwave.dtos.FlutterwaveResponse;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings({"unchecked", "rawtypes"})
@Slf4j
public class Helper {
    public static ObjectMapper MAPPER = new ObjectMapper();
    public static RestConnector REST_CONNECTOR = new RestConnector();

    public static Response buildFlutterwaveResponse(String rawResponse, Boolean useRaw) {
        FlutterwaveResponse flwResponse = null;
        try {
            flwResponse = MAPPER.readValue(rawResponse, FlutterwaveResponse.class);
            log.info("Flutterwave Raw Response");
            log.info(Helper.MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(flwResponse));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Response response = new Response();
        BeanUtils.copyProperties(flwResponse, response);
        if (flwResponse.getStatus() != null && flwResponse.getStatus().equalsIgnoreCase("error")) {
            response.setHasError(true);
        }
        else {
            try {
                HashMap<String, Object> flwResData = (HashMap) response.getData();
                HashMap<String, Object> flwResDataData = null;
                if (flwResData != null && flwResData.size() == 0) {
                    response.setHasError(true);
                }
                flwResDataData = (HashMap) flwResData.get("data");
                if ( flwResDataData != null) {
                    if(((String) flwResDataData.get("responsemessage")).equalsIgnoreCase("successful")) {
                        response.setHasError(false);
                    }
                    else {
                        response.setHasError(true);
                    }
                    HashMap<String, Object> flwResTxData = (HashMap) flwResData.get("tx");
                    response.setData(flwResTxData);
                    response.setMessage((String) flwResDataData.get("responsemessage"));
                }
                else {
                    if (flwResData != null) {
                        response.setMessage((String) flwResData.get("chargeResponseMessage"));   
                        response.setData(flwResData);                                      
                    }
    
                }
                if (!useRaw) {
                    response.setData(marshallFlwData(response.getData()));            
                }
            } catch (ClassCastException e) {
                e.printStackTrace();
                ArrayList<HashMap<String, Object>> flwResData = (ArrayList) response.getData();
                response.setData(flwResData);
                if (!useRaw) {
                    response.setData(marshallArrayFlwData(flwResData));            
                }
            }
        }
        try {
            log.info("Flutterwave process completed (RESPONSE Stage 1)");
            log.info(MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(response));
            log.info("Flutterwave process completed (RESPONSE Stage 2)");
            log.info(MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(response));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static List<FlutterwaveResData> marshallArrayFlwData(ArrayList<HashMap<String, Object>> flwResData) {
        List<FlutterwaveResData> data = new ArrayList<>();
        for (Object each : flwResData) {
            data.add(marshallFlwData(each));
        }
        return data;
    }

    public static FlutterwaveResData marshallFlwData(Object rawData) {
        FlutterwaveResData data = new FlutterwaveResData();
        if (rawData != null) {
            HashMap<String, Object> paymentData = (HashMap<String, Object>) rawData;
            data.setTransactionId(paymentData.get("id"));
            data.setTxId(paymentData.get("txid"));
            data.setAmount(paymentData.get("amount") );
            data.setChargedAmount(paymentData.get("charged_amount"));
            if (data.getChargedAmount() == null) {
                data.setChargedAmount(paymentData.get("chargedamount"));                
            }
            data.setFlwRef(paymentData.get("flwRef"));
            data.setOrderRef(paymentData.get("orderRef"));
            data.setPaymentId(paymentData.get("paymentId"));
            data.setTxRef(paymentData.get("txRef"));
            data.setAppFee(paymentData.get("appfee"));
            data.setDeviceFingerprint(paymentData.get("device_fingerprint"));
            data.setRaveRef(paymentData.get("raveRef"));
            data.setRedirectUrl(paymentData.get("redirectUrl"));    
            data.setIp(paymentData.get("ip"));
            data.setCurrency(paymentData.get("currency"));
            data.setStatus(paymentData.get("status"));
            HashMap<String, String> cardObj = (HashMap<String, String>) paymentData.get("card"); 
            if (cardObj != null) {
                data.getCard().setToken(cardObj.get("life_time_token"));        
                data.getCard().setMonth(cardObj.get("expirymonth"));        
                data.getCard().setYear(cardObj.get("expiryyear"));       
                data.getCard().setLast4(cardObj.get("last4digits"));       
            }
        }
        return data;
    }

    public static String anyToString(Object arg) {
        try {
            return MAPPER.writeValueAsString(arg);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
