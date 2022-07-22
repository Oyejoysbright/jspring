package org.jpc.jspring.flutterwave;

import org.jpc.jspring.enums.CurrencyEnum;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FlutterwavePayment {
    private static FlutterwavePayment instance = null;
    protected static ConfigFlutterwave config = null;

    public final PayWithCard payWithCard = new PayWithCard();
    public final PayWithMoMo payWithMoMo = new PayWithMoMo();
    public final OtpManager otpManager = new OtpManager();
    
    public static FlutterwavePayment initiate(CurrencyEnum defaultCurrency, String...keys) {
        FlutterwavePayment.instance = new FlutterwavePayment();
        FlutterwavePayment.config = new ConfigFlutterwave(keys[0], keys[1], keys[2], defaultCurrency);
        log.info("Flutterwave Payment Instantiated");
        return FlutterwavePayment.instance;
    }

}
