package org.jpc.jspring.enums;

public enum FlutterEndPointEnum {
    TX_VERIFICATION("https://api.flutterwave.com/v3/transactions/"),
    BANKS("https://api.flutterwave.com/v3/banks/"),
    BANK_ACC_VERIFICATION("https://api.flutterwave.com/v3/accounts/resolve"),
    OTP("https://api.flutterwave.com/v3/otps/");

    public final String url;

    public String forTxVerification(int transactionId) {
        return this.url+transactionId+"/verify";
    }

    public String forOtpValidation(String ref) {
        return this.url+ref+"/validate";
    }

    public String forGetBanks(String countryCode) {
        return this.url+countryCode;
    }

    private FlutterEndPointEnum(String url) {
        this.url = url;
    }
}
