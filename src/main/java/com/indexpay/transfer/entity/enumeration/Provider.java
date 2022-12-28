package com.indexpay.transfer.entity.enumeration;

import com.indexpay.transfer.exception.InvalidEnumException;
import org.springframework.util.StringUtils;

public enum Provider {
    PAYSTACK("paystack"),
    FLUTTER_WAVE("flutterwave");

    private final String name;
    Provider(String name) {
        this.name = name;
    }
    public static String ensureProviderIsValid(String provider) throws InvalidEnumException {
        if (!StringUtils.hasText(provider)) {
            return Provider.PAYSTACK.name;
        }
        return Provider.valueOf(provider).name;
    }
}