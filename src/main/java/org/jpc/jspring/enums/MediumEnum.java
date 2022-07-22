package org.jpc.jspring.enums;

public enum MediumEnum {
    SMS("sms"),
    EMAIL("email"),
    WHATSAPP("whatsapp");

    public final String text;

    private MediumEnum(String text) {
        this.text = text;
    }
}
