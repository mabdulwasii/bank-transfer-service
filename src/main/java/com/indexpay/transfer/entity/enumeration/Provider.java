package com.indexpay.transfer.entity.enumeration;

import com.indexpay.transfer.exception.InvalidEnumException;
import org.springframework.util.StringUtils;

public enum Provider {
    PAYSTACK,
    FLUTTERWAVE;

    public static String ensureProviderIsValid(String provider) throws InvalidEnumException {
        if (!StringUtils.hasText(provider)) {
            return Provider.PAYSTACK.name().toLowerCase();
        }
        return Provider.valueOf(provider.toUpperCase()).name().toLowerCase();
    }
}