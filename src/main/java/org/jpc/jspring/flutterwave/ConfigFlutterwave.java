package org.jpc.jspring.flutterwave;

import org.jpc.jspring.enums.CountryCodeEnum;
import org.jpc.jspring.enums.CurrencyEnum;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class ConfigFlutterwave {
    private final String secKey, pubKey, encKey, currency, countryCode;
    private final HttpHeaders headers = new HttpHeaders();
    private final HttpEntity<Object> httpEntity;

    public ConfigFlutterwave(String secKey, String pubKey, String encKey, CurrencyEnum currency, CountryCodeEnum countryCode) {
        this.secKey = secKey;
        this.pubKey = pubKey;
        this.encKey = encKey;
        this.currency = currency.toString();
        this.countryCode = countryCode.toString();
        this.headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + secKey);
        this.httpEntity = new HttpEntity<>(headers);

        log.info("Flutterwave configured");
    }
}
