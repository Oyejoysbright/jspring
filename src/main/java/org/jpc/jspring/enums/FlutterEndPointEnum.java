package org.jpc.jspring.enums;

public enum FlutterEndPointEnum {
    VERIFICATION("https://api.flutterwave.com/v3/transactions/"),
    OTP("https://api.flutterwave.com/v3/otps/");

    public final String url;

    public String forTxVerification(int transactionId) {
        return this.url+transactionId+"/verify";
    }

    public String forOtpValidation(String ref) {
        return this.url+ref+"/validate";
    }

    private FlutterEndPointEnum(String url) {
        this.url = url;
    }
}
