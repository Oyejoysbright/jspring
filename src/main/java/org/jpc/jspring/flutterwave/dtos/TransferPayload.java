package org.jpc.jspring.flutterwave.dtos;

import org.jpc.jspring.enums.CurrencyEnum;

import com.flutterwave.rave.java.payload.metaTransferpayload;

import lombok.Data;

@Data
public class TransferPayload { 
    private String accountBankCode;
    private String accountNumber;
    private String amount;
    private String narration = "JSpring payment manager";
    private CurrencyEnum currency;
    private String reference;
    private metaTransferpayload meta;
    private String beneficiaryName;
    private String destinationBranchCode;
}
