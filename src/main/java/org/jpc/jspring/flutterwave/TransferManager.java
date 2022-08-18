package org.jpc.jspring.flutterwave;

import org.jpc.jspring.core.Response;
import org.jpc.jspring.flutterwave.dtos.TransferPayload;
import org.jpc.jspring.utils.Helper;
import org.springframework.beans.BeanUtils;

import com.flutterwave.rave.java.entry.transfers;
import com.flutterwave.rave.java.payload.transferPayload;

public class TransferManager {
    private String rawResponse = null;

    public Response single (TransferPayload payload) {
        transferPayload flwPayload = new transferPayload();
        BeanUtils.copyProperties(payload, flwPayload);
        
        flwPayload.setSeckey(FlutterwavePayment.config.getSecKey());
        
        flwPayload.setAccount_bank(payload.getAccountBankCode());
        flwPayload.setAccount_number(payload.getAccountNumber());
        flwPayload.setBeneficiary_name(payload.getBeneficiaryName());
        flwPayload.setCurrency(payload.getCurrency() != null? payload.getCurrency().toString() : FlutterwavePayment.config.getCurrency());
        flwPayload.setDestination_branch_code(payload.getDestinationBranchCode());
        rawResponse = new transfers().dotransfer(flwPayload);
        return Helper.buildFlutterwaveResponse(rawResponse, false);
    }
}
